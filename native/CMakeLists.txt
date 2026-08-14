cmake_minimum_required(VERSION 3.10)
project(JavaREmatch)

find_package(Java REQUIRED)
include(UseJava)

message(STATUS "javac path: ${Java_JAVAC_EXECUTABLE}")
message(STATUS "java path:  ${Java_JAVA_EXECUTABLE}")

set(JAVACPP_VERSION "1.5.13" CACHE STRING "JavaCPP version")

if(WIN32)
    set(MAVEN_REPO "$ENV{USERPROFILE}/.m2/repository")
else()
    set(MAVEN_REPO "$ENV{HOME}/.m2/repository")
endif()

find_jar(
    JAVACPP_JAR
    NAMES "javacpp-${JAVACPP_VERSION}"
    PATHS "${MAVEN_REPO}/org/bytedeco/javacpp"
    PATH_SUFFIXES "${JAVACPP_VERSION}"
)

set(OUT_DIR ${CMAKE_BINARY_DIR}/bin)

file(GLOB_RECURSE JAVACPP_SOURCES
    "${CMAKE_SOURCE_DIR}/../src/main/java/cl/rematch/internal/*.java"
)

file(GLOB JAVA_SOURCES
    "${CMAKE_SOURCE_DIR}/../src/main/java/cl/rematch/*.java"
)

set(NATIVE_PATH
    "${CMAKE_SOURCE_DIR}"
)

# 1. Incluye las rutas de encabezados necesarias para encontrar archivos .hpp
find_package(REmatch REQUIRED)

get_target_property(REMATCH_INCLUDES
    REmatch::REmatch
    INTERFACE_INCLUDE_DIRECTORIES
)

get_target_property(REMATCH_DLL
    REmatch::REmatch
    IMPORTED_LOCATION_RELEASE
)

# 2. Definir nombre JNI
set(JNI_WRAPPER_LIBRARY jniREmatch)

# 3. Archivo de salida de compilación Java, usado para saber si se debe compilar javacpp
set(JAVA_CLASSES_STAMP "${CMAKE_BINARY_DIR}/compiled.stamp")

# 3.5 Compila los .java solo si se necesita para el .so
add_custom_command(
    OUTPUT ${JAVA_CLASSES_STAMP}
    COMMAND ${Java_JAVAC_EXECUTABLE} 
            -cp ${JAVACPP_JAR}
            -d ${CMAKE_BINARY_DIR}/classes
            ${JAVACPP_SOURCES}
    COMMAND ${CMAKE_COMMAND} -E touch ${JAVA_CLASSES_STAMP}
    DEPENDS ${JAVACPP_SOURCES}
    COMMENT "Compilando archivos .java a .class"
)

# 4. Archivo esperado del JNI
if(MSVC)
    set(JNI_LIBRARY_W_EXTENSION "jniREmatch.dll")
    set(JNI_OUTPUT_FILE "${CMAKE_BINARY_DIR}/jni/jniREmatch.dll")
else()
    set(JNI_LIBRARY_W_EXTENSION "libjniREmatch.so")
    set(JNI_OUTPUT_FILE "${CMAKE_BINARY_DIR}/jni/libjniREmatch.so")
endif()

# 5. Generar JNI .so solo si aun no existe
set(JAVA_OPTS
    -cp "${CMAKE_BINARY_DIR}/classes"
    -o ${JNI_WRAPPER_LIBRARY}
)

if(MSVC)
    list(APPEND JAVA_OPTS
        -Xcompiler "/std:c++17"
        -Xcompiler "/MT"
    )
endif()

message(${JAVACPP_JAR})

add_custom_command(
    OUTPUT ${JNI_OUTPUT_FILE}
    COMMAND ${Java_JAVA_EXECUTABLE}
            -jar ${JAVACPP_JAR}
            ${JAVA_OPTS}
            "-Dplatform.includepath=${REMATCH_INCLUDES};${NATIVE_PATH}"
            cl.rematch.internal.REmatchConfig
            cl.rematch.internal.Flags
            cl.rematch.internal.Query
            cl.rematch.internal.Match
            cl.rematch.internal.OptionalMatch
            cl.rematch.internal.OptionalMultiMatch
            cl.rematch.internal.Span
            cl.rematch.internal.StringSpanMap
            cl.rematch.internal.MatchIterator
            cl.rematch.internal.MatchGenerator
            cl.rematch.internal.StringVector
            cl.rematch.internal.MatchVector
            cl.rematch.internal.SpanVector
            cl.rematch.internal.MultiQuery
            cl.rematch.internal.MultiMatch
            cl.rematch.internal.MultiMatchVector
            cl.rematch.internal.MultiMatchIterator
            cl.rematch.internal.MultiMatchGenerator
            cl.rematch.internal.Reader
            cl.rematch.internal.ReaderWrapper
    DEPENDS
        ${JAVA_CLASSES_STAMP}
        ${CMAKE_CURRENT_SOURCE_DIR}/reader_wrapper_java.hpp
    VERBATIM
)

# 6. Crea un target para el JNI .so
add_custom_target(GenerateJNIWrapper ALL
    DEPENDS ${JNI_OUTPUT_FILE}
)
