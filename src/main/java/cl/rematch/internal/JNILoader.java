package cl.rematch.internal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bytedeco.javacpp.*;

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
            throw new RuntimeException("The library " + libName + " could not be loaded.", io);
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
