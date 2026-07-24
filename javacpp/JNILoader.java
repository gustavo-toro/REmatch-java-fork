package javacpp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class JNILoader {

    public static void load(String libName) {
        // try {
        // System.loadLibrary(libName);
        // } catch (UnsatisfiedLinkError e) {
        // try {
        // // String pathInJar = "/lib" + libName + ".so"; // TODO: fix this
        // String pathInJar = "/" + libName + ".dll";
        // NativeLoader.loadLibraryFromJar(pathInJar);
        // } catch (IOException io) {
        // throw new RuntimeException("No se pudo cargar " + libName, io);
        // }
        // }

        try {
            Path dir = Files.createTempDirectory("REmatch-bin");

            Path cppDll = NativeLoader.extractTo(
                    "/REmatch.dll",
                    dir.resolve("REmatch.dll"));

            Path jniDll = NativeLoader.extractTo(
                    "/jniREmatch.dll",
                    dir.resolve("jniREmatch.dll"));

            System.load(cppDll.toAbsolutePath().toString());
            System.load(jniDll.toAbsolutePath().toString());
        } catch (Exception io) {
            throw new RuntimeException("No se pudo cargar " + libName, io);
        }
    }
}
