package it.unibo.oop.lab.advanced;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**You'd want this class to be generic with i.e. T extends FromString
 * (it doesn't exist but that's my idea)
 */
public final class YAMLParser {
    private final Map<String, Integer> result;

    private final class Pair<A, B> {
        private final A a;
        private final B b;
        Pair(final A first, final B second) {
            this.a = first;
            this.b = second;
        }
    }

    public YAMLParser(final String yaml) {
        this.result = List.of(yaml.replace(": ", ":").split("\n")).stream().map((s) -> {
            return s.split(":");
            })
        .map((e) -> {
            return new Pair<>(e[0], Integer.parseInt(e[1]));
        })
        .collect(Collectors.toMap((o) -> {
            return o.a;
        }, (o) -> {
            return o.b;
        }));
    }
    public Map<String, Integer> getResult() {
        return this.result;
    }
}
