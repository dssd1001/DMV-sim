import java.util.ArrayList;

public class HeapOfLicenses {

    private ArrayList<License> heap;

    public HeapOfLicenses() {
        heap = new ArrayList<License>();
        heap.add(null);
    }

    public String toString() {
        String s = "";
        for (int i = 1; i < heap.size(); i++) {
            s += heap.get(i).Person.toString() + " | ";
        }
        return s;
    }

    public void add(License l, String category) {
        heap.add(l);
        bubbleUp(category);
    }

    public License removeRoot(String category) {
        License lastL = heap.remove(heap.size() - 1);
        if (heap.size() <= 1) {
            return lastL;
        } else {
            License rootL = heap.set(1, lastL);
            bubbleDown(category);
            return rootL;
        }
    }

    private void bubbleUp(String category) {
        int curIndex = heap.size() - 1;
        int parentIndex = curIndex / 2;
        License cur = heap.get(curIndex);

        if (category.equalsIgnoreCase("name")) {
            while ((curIndex > 1) && (cur.Person.lname.compareTo(heap.get(parentIndex).Person.lname) < 0)) {
                License parent = heap.get(parentIndex);
                heap.set(parentIndex, cur);
                heap.set(curIndex, parent);

                curIndex = parentIndex;
                parentIndex = curIndex / 2;
            }
        }
        if (category.equalsIgnoreCase("ltype")) {
            while ((curIndex > 1) && (cur.LicenseType.compareTo(heap.get(parentIndex).LicenseType) < 0)) {
                License parent = heap.get(parentIndex);
                heap.set(parentIndex, cur);
                heap.set(curIndex, parent);

                curIndex = parentIndex;
                parentIndex = curIndex / 2;
            }
        }
        if (category.equalsIgnoreCase("eyec")) {
            while ((curIndex > 1) && (cur.Person.EyesColor.compareTo(heap.get(parentIndex).Person.EyesColor) < 0)) {
                License parent = heap.get(parentIndex);
                heap.set(parentIndex, cur);
                heap.set(curIndex, parent);

                curIndex = parentIndex;
                parentIndex = curIndex / 2;
            }
        }
        if (category.equalsIgnoreCase("hairc")) {
            while ((curIndex > 1) && (cur.Person.HairColor.compareTo(heap.get(parentIndex).Person.HairColor) < 0)) {
                License parent = heap.get(parentIndex);
                heap.set(parentIndex, cur);
                heap.set(curIndex, parent);

                curIndex = parentIndex;
                parentIndex = curIndex / 2;
            }
        }
        if (category.equalsIgnoreCase("gender")) {
            while ((curIndex > 1) && (cur.Person.Gender.compareTo(heap.get(parentIndex).Person.Gender) < 0)) {
                License parent = heap.get(parentIndex);
                heap.set(parentIndex, cur);
                heap.set(curIndex, parent);

                curIndex = parentIndex;
                parentIndex = curIndex / 2;
            }
        }
    }

    private void bubbleDown(String category) {
        int curIndex = 1;
        int child1Index = curIndex * 2;
        int child2Index = curIndex * 2 + 1;
        int swapIndex = 1;
        License cur = heap.get(curIndex);

        if (category.equalsIgnoreCase("name")) {
            while ((child1Index < heap.size()) && (swapIndex > 0)) {
                swapIndex = -1;
                if (child2Index < heap.size()) {
                    License child1 = heap.get(child1Index);
                    License child2 = heap.get(child2Index);
                    if ((child1.Person.lname.compareTo(child2.Person.lname) <= 0) && (cur.Person.lname.compareTo(child1.Person.lname) > 0)) {
                        swapIndex = child1Index;
                    } else if ((child2.Person.lname.compareTo(child1.Person.lname) < 0) && (cur.Person.lname.compareTo(child2.Person.lname) > 0)) {
                        swapIndex = child2Index;
                    }
                } else if (cur.Person.lname.compareTo(heap.get(child1Index).Person.lname) > 0) {
                    swapIndex = child1Index;
                }

                if (swapIndex != -1) {
                    License swapVal = heap.get(swapIndex);
                    heap.set(swapIndex, cur);
                    heap.set(curIndex, swapVal);

                    curIndex = swapIndex;
                    cur = heap.get(curIndex);
                    child1Index = curIndex * 2;
                    child2Index = curIndex * 2 + 1;
                }
            }
        }
        if (category.equalsIgnoreCase("ltype")) {
            while ((child1Index < heap.size()) && (swapIndex > 0)) {
                swapIndex = -1;
                if (child2Index < heap.size()) {
                    License child1 = heap.get(child1Index);
                    License child2 = heap.get(child2Index);
                    if ((child1.LicenseType.compareTo(child2.LicenseType) <= 0) && (cur.LicenseType.compareTo(child1.LicenseType) > 0)) {
                        swapIndex = child1Index;
                    } else if ((child2.LicenseType.compareTo(child1.LicenseType) < 0) && (cur.LicenseType.compareTo(child2.LicenseType) > 0)) {
                        swapIndex = child2Index;
                    }
                } else if (cur.LicenseType.compareTo(heap.get(child1Index).LicenseType) > 0) {
                    swapIndex = child1Index;
                }

                if (swapIndex != -1) {
                    License swapVal = heap.get(swapIndex);
                    heap.set(swapIndex, cur);
                    heap.set(curIndex, swapVal);

                    curIndex = swapIndex;
                    cur = heap.get(curIndex);
                    child1Index = curIndex * 2;
                    child2Index = curIndex * 2 + 1;
                }
            }
        }
        if (category.equalsIgnoreCase("eyec")) {
            while ((child1Index < heap.size()) && (swapIndex > 0)) {
                swapIndex = -1;
                if (child2Index < heap.size()) {
                    License child1 = heap.get(child1Index);
                    License child2 = heap.get(child2Index);
                    if ((child1.Person.EyesColor.compareTo(child2.Person.EyesColor) <= 0) && (cur.Person.EyesColor.compareTo(child1.Person.EyesColor) > 0)) {
                        swapIndex = child1Index;
                    } else if ((child2.Person.EyesColor.compareTo(child1.Person.EyesColor) < 0) && (cur.Person.EyesColor.compareTo(child2.Person.EyesColor) > 0)) {
                        swapIndex = child2Index;
                    }
                } else if (cur.Person.EyesColor.compareTo(heap.get(child1Index).Person.EyesColor) > 0) {
                    swapIndex = child1Index;
                }

                if (swapIndex != -1) {
                    License swapVal = heap.get(swapIndex);
                    heap.set(swapIndex, cur);
                    heap.set(curIndex, swapVal);

                    curIndex = swapIndex;
                    cur = heap.get(curIndex);
                    child1Index = curIndex * 2;
                    child2Index = curIndex * 2 + 1;
                }
            }
        }
        if (category.equalsIgnoreCase("hairc")) {
            while ((child1Index < heap.size()) && (swapIndex > 0)) {
                swapIndex = -1;
                if (child2Index < heap.size()) {
                    License child1 = heap.get(child1Index);
                    License child2 = heap.get(child2Index);
                    if ((child1.Person.HairColor.compareTo(child2.Person.HairColor) <= 0) && (cur.Person.HairColor.compareTo(child1.Person.HairColor) > 0)) {
                        swapIndex = child1Index;
                    } else if ((child2.Person.HairColor.compareTo(child1.Person.HairColor) < 0) && (cur.Person.HairColor.compareTo(child2.Person.HairColor) > 0)) {
                        swapIndex = child2Index;
                    }
                } else if (cur.Person.HairColor.compareTo(heap.get(child1Index).Person.HairColor) > 0) {
                    swapIndex = child1Index;
                }

                if (swapIndex != -1) {
                    License swapVal = heap.get(swapIndex);
                    heap.set(swapIndex, cur);
                    heap.set(curIndex, swapVal);

                    curIndex = swapIndex;
                    cur = heap.get(curIndex);
                    child1Index = curIndex * 2;
                    child2Index = curIndex * 2 + 1;
                }
            }
        }
        if (category.equalsIgnoreCase("gender")) {
            while ((child1Index < heap.size()) && (swapIndex > 0)) {
                swapIndex = -1;
                if (child2Index < heap.size()) {
                    License child1 = heap.get(child1Index);
                    License child2 = heap.get(child2Index);
                    if ((child1.Person.Gender.compareTo(child2.Person.Gender) <= 0) && (cur.Person.Gender.compareTo(child1.Person.Gender) > 0)) {
                        swapIndex = child1Index;
                    } else if ((child2.Person.Gender.compareTo(child1.Person.Gender) < 0) && (cur.Person.Gender.compareTo(child2.Person.Gender) > 0)) {
                        swapIndex = child2Index;
                    }
                } else if (cur.Person.Gender.compareTo(heap.get(child1Index).Person.Gender) > 0) {
                    swapIndex = child1Index;
                }

                if (swapIndex != -1) {
                    License swapVal = heap.get(swapIndex);
                    heap.set(swapIndex, cur);
                    heap.set(curIndex, swapVal);

                    curIndex = swapIndex;
                    cur = heap.get(curIndex);
                    child1Index = curIndex * 2;
                    child2Index = curIndex * 2 + 1;
                }
            }
        }
    }

}