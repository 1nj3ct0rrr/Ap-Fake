package anywheresoftware.b4a.objects.collections;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@BA.ShortName("Map")
public class Map extends AbsObjectWrapper<MyMap> implements BA.B4aDebuggable {
    public void Initialize() {
        setObject(new MyMap());
    }

    public Object Put(Object Key, Object Value) {
        return ((MyMap) getObject()).put(Key, Value);
    }

    public Object Remove(Object Key) {
        return ((MyMap) getObject()).remove(Key);
    }

    public Object Get(Object Key) {
        return ((MyMap) getObject()).get(Key);
    }

    public Object GetDefault(Object Key, Object Default) {
        Object res = ((MyMap) getObject()).get(Key);
        return res == null ? Default : res;
    }

    public void Clear() {
        ((MyMap) getObject()).clear();
    }

    public Object GetKeyAt(int Index) {
        return ((MyMap) getObject()).getKey(Index);
    }

    public Object GetValueAt(int Index) {
        return ((MyMap) getObject()).getValue(Index);
    }

    public int getSize() {
        return ((MyMap) getObject()).size();
    }

    public boolean ContainsKey(Object Key) {
        return ((MyMap) getObject()).containsKey(Key);
    }

    public BA.IterableList Keys() {
        return new BA.IterableList() {
            /* class anywheresoftware.b4a.objects.collections.Map.AnonymousClass1 */

            @Override // anywheresoftware.b4a.BA.IterableList
            public Object Get(int index) {
                return Map.this.GetKeyAt(index);
            }

            @Override // anywheresoftware.b4a.BA.IterableList
            public int getSize() {
                return Map.this.getSize();
            }
        };
    }

    public BA.IterableList Values() {
        return new BA.IterableList() {
            /* class anywheresoftware.b4a.objects.collections.Map.AnonymousClass2 */

            @Override // anywheresoftware.b4a.BA.IterableList
            public Object Get(int index) {
                return Map.this.GetValueAt(index);
            }

            @Override // anywheresoftware.b4a.BA.IterableList
            public int getSize() {
                return Map.this.getSize();
            }
        };
    }

    @Override // anywheresoftware.b4a.BA.B4aDebuggable
    @BA.Hide
    public Object[] debug(int limit, boolean[] outShouldAddReflectionFields) {
        Object[] res = new Object[((Math.min(getSize(), limit) + 1) * 2)];
        res[0] = "Size";
        res[1] = Integer.valueOf(getSize());
        int i = 2;
        for (Map.Entry<Object, Object> e : ((MyMap) getObject()).entrySet()) {
            if (i >= res.length - 1) {
                break;
            }
            res[i] = String.valueOf(e.getKey());
            if (res[i].toString().length() == 0) {
                res[i] = "(empty string)";
            }
            res[i + 1] = e.getValue();
            i += 2;
        }
        outShouldAddReflectionFields[0] = false;
        return res;
    }

    @BA.Hide
    public static class MyMap implements java.util.Map<Object, Object> {
        private Map.Entry<Object, Object> currentEntry;
        private LinkedHashMap<Object, Object> innerMap = new LinkedHashMap<>();
        private Iterator<Map.Entry<Object, Object>> iterator;
        private int iteratorPosition;

        public Object getKey(int index) {
            return getEntry(index).getKey();
        }

        public Object getValue(int index) {
            return getEntry(index).getValue();
        }

        private Map.Entry<Object, Object> getEntry(int index) {
            if (!(this.iterator == null || this.iteratorPosition == index)) {
                if (this.iteratorPosition == index - 1) {
                    this.currentEntry = this.iterator.next();
                    this.iteratorPosition++;
                } else {
                    this.iterator = null;
                }
            }
            if (this.iterator == null) {
                this.iterator = this.innerMap.entrySet().iterator();
                for (int i = 0; i <= index; i++) {
                    this.currentEntry = this.iterator.next();
                }
                this.iteratorPosition = index;
            }
            return this.currentEntry;
        }

        public void clear() {
            this.iterator = null;
            this.innerMap.clear();
        }

        public boolean containsKey(Object key) {
            return this.innerMap.containsKey(key);
        }

        public boolean containsValue(Object value) {
            return this.innerMap.containsValue(value);
        }

        @Override // java.util.Map
        public Set<Map.Entry<Object, Object>> entrySet() {
            return this.innerMap.entrySet();
        }

        @Override // java.util.Map
        public Object get(Object key) {
            return this.innerMap.get(key);
        }

        public boolean isEmpty() {
            return this.innerMap.isEmpty();
        }

        @Override // java.util.Map
        public Set<Object> keySet() {
            return this.innerMap.keySet();
        }

        @Override // java.util.Map
        public Object put(Object key, Object value) {
            this.iterator = null;
            return this.innerMap.put(key, value);
        }

        @Override // java.util.Map
        public void putAll(java.util.Map<? extends Object, ? extends Object> m) {
            this.iterator = null;
            this.innerMap.putAll(m);
        }

        @Override // java.util.Map
        public Object remove(Object key) {
            this.iterator = null;
            return this.innerMap.remove(key);
        }

        public int size() {
            return this.innerMap.size();
        }

        @Override // java.util.Map
        public Collection<Object> values() {
            return this.innerMap.values();
        }

        public String toString() {
            return this.innerMap.toString();
        }
    }
}
