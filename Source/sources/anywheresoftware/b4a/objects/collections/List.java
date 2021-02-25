package anywheresoftware.b4a.objects.collections;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

@BA.ShortName("List")
public class List extends AbsObjectWrapper<java.util.List<Object>> implements BA.IterableList {
    public void Initialize() {
        setObject(new ArrayList());
    }

    public void Initialize2(List Array) {
        setObject((java.util.List) Array.getObject());
    }

    public void Clear() {
        ((java.util.List) getObject()).clear();
    }

    public void Add(Object item) {
        Object prev;
        if (BA.debugMode && ((java.util.List) getObject()).size() > 0 && (prev = Get(getSize() - 1)) != null && prev == item && !(prev instanceof String) && !(prev instanceof Number) && !(prev instanceof Boolean)) {
            BA.LogInfo("Warning: same object added to list multiple times.");
        }
        ((java.util.List) getObject()).add(item);
    }

    public void AddAll(List List) {
        ((java.util.List) getObject()).addAll((Collection) List.getObject());
    }

    public void AddAllAt(int Index, List List) {
        ((java.util.List) getObject()).addAll(Index, (Collection) List.getObject());
    }

    public void RemoveAt(int Index) {
        ((java.util.List) getObject()).remove(Index);
    }

    public void InsertAt(int Index, Object Item) {
        ((java.util.List) getObject()).add(Index, Item);
    }

    @Override // anywheresoftware.b4a.BA.IterableList
    public Object Get(int Index) {
        return ((java.util.List) getObject()).get(Index);
    }

    public void Set(int Index, Object Item) {
        ((java.util.List) getObject()).set(Index, Item);
    }

    @Override // anywheresoftware.b4a.BA.IterableList
    public int getSize() {
        return ((java.util.List) getObject()).size();
    }

    public int IndexOf(Object Item) {
        return ((java.util.List) getObject()).indexOf(Item);
    }

    public void Sort(boolean Ascending) {
        if (Ascending) {
            Collections.sort((java.util.List) getObject());
        } else {
            Collections.sort((java.util.List) getObject(), new Comparator<Comparable>() {
                /* class anywheresoftware.b4a.objects.collections.List.AnonymousClass1 */

                public int compare(Comparable o1, Comparable o2) {
                    return o2.compareTo(o1);
                }
            });
        }
    }

    public void SortType(String FieldName, boolean Ascending) throws SecurityException, NoSuchFieldException {
        sortList(FieldName, Ascending, false);
    }

    public void SortTypeCaseInsensitive(String FieldName, boolean Ascending) throws SecurityException, NoSuchFieldException {
        sortList(FieldName, Ascending, true);
    }

    private void sortList(String FieldName, final boolean Ascending, final boolean caseInsensitive) throws SecurityException, NoSuchFieldException {
        if (getSize() != 0) {
            final Field f = Get(0).getClass().getDeclaredField(FieldName);
            f.setAccessible(true);
            Collections.sort((java.util.List) getObject(), new Comparator<Object>() {
                /* class anywheresoftware.b4a.objects.collections.List.AnonymousClass2 */

                @Override // java.util.Comparator
                public int compare(Object o1, Object o2) {
                    int cmp;
                    try {
                        if (caseInsensitive) {
                            cmp = String.valueOf(f.get(o1)).compareToIgnoreCase(String.valueOf(f.get(o2)));
                        } else {
                            cmp = ((Comparable) f.get(o1)).compareTo(f.get(o2));
                        }
                        return (Ascending ? 1 : -1) * cmp;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    public void SortCaseInsensitive(boolean Ascending) {
        if (Ascending) {
            Collections.sort((java.util.List) getObject(), new Comparator<Comparable>() {
                /* class anywheresoftware.b4a.objects.collections.List.AnonymousClass3 */

                public int compare(Comparable o1, Comparable o2) {
                    return o1.toString().compareToIgnoreCase(o2.toString());
                }
            });
        } else {
            Collections.sort((java.util.List) getObject(), new Comparator<Comparable>() {
                /* class anywheresoftware.b4a.objects.collections.List.AnonymousClass4 */

                public int compare(Comparable o1, Comparable o2) {
                    return o2.toString().compareToIgnoreCase(o1.toString());
                }
            });
        }
    }
}
