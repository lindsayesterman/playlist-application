/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Lindsay_Esterman
*/

public class ITunes {
    public static void main(String[] args) {
        // Sample testing code ...
        Playlist pl = new Playlist();
        pl.addFirst("first", 65.0);
        pl.addLast("1", 65.0);
        pl.addLast("2", 65.0);
        pl.addLast("3", 65.0);
        pl.addLast("4", 65.0);
        pl.addLast("RadioLab", 25.5);
        pl.add("Homo deus", 7, 2);
        System.out.println(pl.displayPlaylistForward());
        pl.deleteFirst();
        // pl.addLast("MakeMeSmart", 24.5);
        // pl.addLast("Worldly", 55);
        // pl.addLast("Explained", 23.0);
        // pl.addLast("Invisibilia", 33.5);
        System.out.println(pl.displayPlaylistForward());
        // System.out.println(pl.displayPlaylistBackward());
        // System.out.println(pl.deleteEveryMthEpisode(3));
        // System.out.println(pl.displayPlaylistForward());
        // System.out.println(pl.displayPlaylistBackward());
    }
}
