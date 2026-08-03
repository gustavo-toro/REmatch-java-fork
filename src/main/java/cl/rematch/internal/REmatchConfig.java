package cl.rematch.internal;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(
    value = {
        @Platform(
            includepath = "C:/Program Files (x86)/REmatch/include", // TODO:
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
                "REmatch/query.hpp"
            },

            preloadpath = "C:/Program Files (x86)/REmatch/lib", // TODO:
            preload = "REmatch",

            linkpath = "C:/Program Files (x86)/REmatch/bin", // TODO:
            link = "REmatch"
        )
    }
)
public class REmatchConfig implements InfoMapper {
    public void map(InfoMap infoMap) {
    }
}
