package cl.rematch.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

public class JNILoader {

    public static void load(String libName) {
        // TODO: use default Loader
        String platform = Loader.getPlatform();
        try {
            if (platform.startsWith("windows")) {
                loadWinLibs();
            } else {
                loadLinuxLibs(libName);
            }
        } catch (Exception io) {
            throw new RuntimeException("No se pudo cargar " + libName, io);
        }
    }

    private static void loadWinLibs() throws IOException {
        Path dir = Files.createTempDirectory("REmatch-bin");

        Path cppDll = NativeLoader.extractTo(
                "/REmatch.dll",
                dir.resolve("REmatch.dll"));

        Path jniDll = NativeLoader.extractTo(
                "/jniREmatch.dll",
                dir.resolve("jniREmatch.dll"));

        System.load(cppDll.toAbsolutePath().toString());
        System.load(jniDll.toAbsolutePath().toString());
    }

    private static void loadLinuxLibs(String libName) throws IOException {
        String pathInJar = "/lib" + libName + ".so";
        NativeLoader.loadLibraryFromJar(pathInJar);
    }
}
