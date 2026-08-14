package cl.rematch.internal;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    value = {
        @Platform(
            include = {
                "REmatch/REmatch.hpp",
                "REmatch/span.hpp",
                "REmatch/constants.hpp",
                "REmatch/exceptions.hpp",
                "REmatch/flags.hpp",
                "REmatch/fstream_reader.hpp",
                "REmatch/iterator.hpp",
                "REmatch/match.hpp",
                "REmatch/match_generator.hpp",
                "REmatch/multi_iterator.hpp",
                "REmatch/multi_match.hpp",
                "REmatch/multi_match_generator.hpp",
                "REmatch/multi_query.hpp",
                "REmatch/query.hpp",
                "reader_wrapper_java.hpp"
            },            
            // Build time
            linkpath = "C:/Program Files (x86)/REmatch/lib",
            link = "REmatch",

            // Runtime
            // preload = "REmatch",
            // preloadresource = "cl/rematch/internal/windows-x86_64/REmatch.dll",

            library = "jniREmatch"
        )
    }
)
public class REmatchConfig implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
    }
}
