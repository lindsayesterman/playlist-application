/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Lindsay_Esterman
*/

/*  This class represents a Playlist of podcast episodes, where each
/*  episode is implemented as an object of type Episode. A user navigating
/*  a Playlist should be able to move between songs using Next or Previous.
/*
/*  To enable flexible navigation, the Playlist is implemented as
/*  a Circular Doubly Linked List where each episode has a link to both
/*  the next and the prev episodes in the list.
/*
/*  Additionally, the last Episode is linked to the head of the list (via next),
/*  and the head of the list is linked to that last Episode (via prev). That said,
/*  there is NO special "last" reference keeping track of the last Episode.
/*  But there is a "head" reference that should always refer to the first Episode.
*/
public class Playlist {
    private Episode head;
    private int size;

    public Playlist() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Ensure that "size" is updated properly in other methods, to
    // always reflect the correct number of episodes in the current playlist
    public int getSize() {
        return this.size;
    }

    // Displays the Episodes starting from the head and moving forward
    // Example code and its expected output:
    /*
     * Playlist pl = new Playlist();
     * /* pl.addLast("PlanetMoney",26.0);
     * /* pl.addLast("TodayExplained",10);
     * /* pl.addLast("RadioLab",25.5);
     * /* System.out.println(pl.displayPlaylistForward());
     * /* [BEGIN] (PlanetMoney|26.0MIN) -> (TodayExplained|10.0MIN) -> (RadioLab|
     * 25.5MIN) [END]
     */
    public String displayPlaylistForward() {
        String output = "[BEGIN] ";
        Episode current = head;
        if (current != null) {
            while (current.next != head) {
                output += current + " -> ";
                current = current.next;
            }
            output += current + " [END]\n";
        } else {
            output += " [END]\n";
        }
        return output;
    }

    // Displays the Episodes starting from the end and moving backwards
    // Example code and its expected output:
    /*
     * Playlist pl = new Playlist();
     * /* pl.addLast("PlanetMoney",26.0);
     * /* pl.addLast("HowIBuiltThis",10);
     * /* pl.addLast("RadioLab",25.5);
     * /* System.out.println(pl.displayPlaylistForward());
     * /* [END] (RadioLab|25.5MIN) -> (HowIBuiltThis|10.0MIN) -> (PlanetMoney|
     * 26.0MIN) [BEGIN]
     */
    public String displayPlaylistBackward() {
        String output = "[END] ";
        Episode current = head.prev;
        if (current != null) {
            while (current.prev != head.prev) {
                output += current + " -> ";
                current = current.prev;
            }
            output += current + " [BEGIN]\n";
        } else {
            output += " [BEGIN]\n";
        }
        return output;
    }

    // Add a new Episode at the beginning of the Playlist
    public void addFirst(String title, double length) {
        // // TODO ..
        if (head != null & size != 1) {
            Episode last = head.prev;
            Episode tmp = new Episode(title, length, head, last);
            tmp.next = head;
            tmp.prev = last;
            last.next = tmp;
            head.prev = tmp;
            head = tmp;
            size = size + 1;
        } else if (head == null) {
            // this is right, don't change
            head = new Episode(title, length, null, null);
            head.next = head;
            head.prev = head;
            size = size + 1;
        } else {
            Episode oldFront = head;
            Episode a = new Episode(title, length, oldFront, oldFront);
            oldFront.prev = a;
            oldFront.next = a;
            head = a;
            size = size + 1;
        }
    }

    // Add a new Episode at the end of the Playlist
    public void addLast(String title, double length) {
        if (head != null) {
            Episode last = head.prev;
            Episode tmp = new Episode(title, length, head, last);
            head.prev = tmp;
            last.next = tmp;
            size = size + 1;
        } else if (head == null) {
            Episode tmp = new Episode(title, length, head, head);
            tmp.prev = tmp;
            tmp.next = tmp;
            head = tmp;
            size = size + 1;
        }
    }

    // Add a new Episode at the given index, assuming that index
    // zero corresponds to the first node
    public void add(String title, double length, int index) {
        // Coding strategy: Start by solving the general case, then add checks
        // for corner cases (e.g. What if "pos" is out-of-bound?)
        if (size < index) {
            throw new RuntimeException("[Error] Cannot add episode to index outside of playlist");
        } else if (head == null) {
            addFirst(title, length);
        } else {
            Episode newEp = new Episode(title, length, null, null);
            Episode temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newEp.next = temp.next;
            temp.next.prev = newEp;
            temp.next = newEp;
            newEp.prev = temp;
            size = size + 1;
        }
    }

    // Delete the first Episode in the Playlist
    public Episode deleteFirst() {
        // TODO .. //
        if (head != null) {
            Episode oldHead = head;
            if (size == 1) {
                head = null;
                size = size - 1;
            } else {
                Episode last = head.prev;
                last.next = head.next;
                head.next.prev = last;
                size = size - 1;
                head = head.next;
            }
            return oldHead;
        } else {
            throw new RuntimeException("[Error] Cannot delete episode from an empty Playlist!");
        }
    }

    // Delete the last Episode in the Playlist
    // (There is no special "last" variable in this Playlist;
    // think of alternative ways to find that last Episode)
    public Episode deleteLast() {
        // TODO .. //
        if (head != null) {
            if (size > 1) {
                Episode temp = head;
                while (temp.next.next != head) {
                    temp = temp.next;
                }
                Episode lastNode = temp.next;
                temp.next = head;
                head.prev = temp;
                size = size - 1;
                return lastNode;
            } else {
                Episode oldHead = head;
                head = null;
                size = size - 1;
                return oldHead;
            }
        } else {
            throw new RuntimeException("[Error] Cannot delete episode from an empty Playlist!");
        }
    }

    // Remove (delete) the Episode that has the given "title"
    // You can assume there will be no duplicate titles in any Playlist
    public Episode deleteEpisode(String title) {
        if (head == null) {
            throw new RuntimeException("[Error] Cannot delete episode from an empty Playlist!");
        }
        Episode curr = head;
        Episode prev = null;
        while (!curr.getTitle().equals(title)) {
            if (curr.next == head) {
                throw new RuntimeException("[Error] There is no episode with that title.");
            }
            prev = curr;
            curr = curr.next;
        }

        if (size == 1) {
            head = null;
            return head;
        }

        else if (curr.next == head) {
            prev.next = head;
            head.prev = prev;
        } else {
            Episode temp = curr.next;
            prev.next = temp;
            temp.prev = prev;
        }
        return curr;
    }

    //

    // Remove (delete) every m-th Episode in the user's circular Playlist,
    // until only one Episode survives. Return the survived Episode.
    // when you reach every third, you try to delete the same way you would delete
    // any episode (big diff from removeAnyEpisode is checking. Def make sure you
    // update head at end to whichever is last standing. Not every time you delete.
    // To find third curr.next.next.next. For loop doesn't work - too complciated -
    // use while.)

    // stopping condition: one left - check by while size > 1 USE DELETE EPISODE
    // LEVEL WHICH UPDATES SIZE. Inside while loop check whether it's third
    public Episode deleteEveryMthEpisode(int m) {
        // TODO .. //
        // Episode temp = head;
        // int count = 0;
        // while (this.size > 1) {
        // if (count % 3 == 0) {
        // deleteEpisode(temp.getTitle());
        // }
        // temp = temp.next;
        // count++;
        // }
        // return temp;
        return null;
    }

}
// End of Playlist class