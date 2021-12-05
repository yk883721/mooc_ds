package tree;

import java.util.Objects;

public class TwoThreeTree<K extends Comparable<K>, V> {


    


    class KeyValuePair{

        public K key;
        public V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyValuePair that = (KeyValuePair) o;
            return Objects.equals(key, that.key);
        }

    }

}
