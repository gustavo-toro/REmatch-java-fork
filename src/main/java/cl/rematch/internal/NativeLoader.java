package cl.rematch.internal;

import java.io.*;
import java.nio.file.*;

public class NativeLoader {

    public static void loadLibraryFromJar(String pathInJar) throws IOException {
        // Obtiene el nombre del archivo nativo
        String fileName = Paths.get(pathInJar).getFileName().toString();

        // Crea un archivo temporal para guardar la biblioteca
        Path temp = Files.createTempFile("jni", "_" + fileName);
        temp.toFile().deleteOnExit();

        // Copia desde el .jar al archivo temporal
        try (InputStream is = NativeLoader.class.getResourceAsStream(pathInJar)) {
            if (is == null)
                throw new FileNotFoundException("No se encontró " + pathInJar + " en el JAR");
            Files.copy(is, temp, StandardCopyOption.REPLACE_EXISTING);
        }

        // Carga desde la ruta temporal
        System.load(temp.toAbsolutePath().toString());
    }

    public static Path extractTo(String resource, Path target) throws IOException {
        Files.createDirectories(target.getParent());

        try (InputStream in = NativeLoader.class.getResourceAsStream(resource)) {
            if (in == null) {
                throw new FileNotFoundException(
                        "Could not find " + resource + " in JAR");
            }

            Files.copy(
                    in,
                    target,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        // target.toFile().deleteOnExit();
        return target;
    }
}
