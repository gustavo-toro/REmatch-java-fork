cmake_minimum_required(VERSION 3.10)
project(JavaREmatch)

find_package(Java REQUIRED)

message(STATUS "javac path: ${Java_JAVAC_EXECUTABLE}")
message(STATUS "java path:  ${Java_JAVA_EXECUTABLE}")

set(OUT_DIR ${CMAKE_BINARY_DIR}/bin)
set(MAIN_CLASS ${CMAKE_SOURCE_DIR}/rematch/Main.java)
set(JAVACPP_JAR ${CMAKE_SOURCE_DIR}/javacpp/libs/javacpp.jar)



file(GLOB_RECURSE JAVACPP_SOURCES
    "${CMAKE_SOURCE_DIR}/../src/main/java/cl/rematch/internal/*.java"
)

file(GLOB JAVA_SOURCES
    "${CMAKE_SOURCE_DIR}/../src/main/java/cl/rematch/*.java"
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
    COMMAND javac 
            # -cp ${JAVACPP_JAR}
            -cp "/home/gustavo/.m2/repository/org/bytedeco/javacpp/1.5.13/javacpp-1.5.13.jar"
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
    -d "${CMAKE_BINARY_DIR}/jni"
    -o ${JNI_WRAPPER_LIBRARY}
)

if(MSVC)
    list(APPEND JAVA_OPTS
        "-Dplatform.includepath=${REMATCH_INCLUDES}"
        "-Dplatform.linkpath=C:/Program Files (x86)/REmatch/lib" # TODO:
        -Xcompiler "/std:c++17"
        -Xcompiler "/MT"
    )
endif()

add_custom_command(
    OUTPUT ${JNI_OUTPUT_FILE}
    COMMAND java
            # -jar ${JAVACPP_JAR}
            -jar "/home/gustavo/.m2/repository/org/bytedeco/javacpp/1.5.13/javacpp-1.5.13.jar" # TODO:
            ${JAVA_OPTS}
            cl.rematch.internal.REmatchConfig
            cl.rematch.internal.REmatch
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
            cl.rematch.internal.JNILoader
            cl.rematch.internal.NativeLoader
            cl.rematch.internal.Reader
            cl.rematch.internal.ReaderWrapper
    COMMAND ${CMAKE_COMMAND} -E remove_directory "${CMAKE_BINARY_DIR}/classes"
    COMMENT "Generando un solo JNI wrapper con javacpp"
    DEPENDS ${JAVA_CLASSES_STAMP}
)

# 6. Crea un target para el JNI .so
add_custom_target(GenerateJNIWrapper ALL
    DEPENDS ${JNI_OUTPUT_FILE}
)
