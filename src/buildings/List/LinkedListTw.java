package buildings.List;
import buildings.Exceptions.SpaceIndexOutOfBoundsException;
import buildings.interfaces.Floor;

import buildings.Floors.OfficeFloor;

import java.io.Serializable;


public class LinkedListTw implements Iterable, Serializable {
    private Node first;
    private int size = 0;
    private class Node implements Serializable {
        Node next;
        Node prev;
        Floor item;
    }
    public LinkedListTw(Floor[] items){
        first = new Node();
        first.next =first;
        first.prev = first;
        for (var v: items) {
            addLast(v);
        }
    }
    public LinkedListTw(int a,int[] flats){
        for (int i = 0; i < a; i++) {
            addLast(new OfficeFloor(flats[i]));
        }
    }
    void addFirst(Floor item)
    {
        Node a = new Node();
        a.item = item;
        a.next = first;
        a.prev = first.prev;
        first = a;

        size++;
    }
    public Floor[] toArray() {
        var resultArray =  new Floor[this.size];
        int index = 0;
        for (Node link = this.first.next; link != first; link = link.next) {
            resultArray[index++] = link.item;
        }

        return resultArray;
    }
    public Floor get(int i){
        var n = first.next;
        if(i == 0){
            return first.next.item;
        }
        for(int j = 1; n!=first && j <i+1;j++){
            n = n.next;
        }
        return n.item;
    }

    public void set(int i,Floor e){
        if(i<size){
            Node n = first.next;
            if(i == 0){
                first.item = e;
            }
            for(int j = 1; n!=first && j <i;j++){
                n = n.next;
            }
            n.item = e;
        }else {
            throw new SpaceIndexOutOfBoundsException("");
        }
    }
    void addLast(Floor item) {
        var a = new Node();
        a.item = item;
        var v = first;
        while (v.next!=first){
            v = v.next;
        }
        v.next = a;
        a.prev = v;
        a.next = first;
        size++;
    }

   public Floor getFirst()
    {
        return first.item;
    }

    public void remove(int i)
    {
        var t = first;
        while (t.next != first && i > 0) {
            t = t.next;
            i--;
        }
        if(t.next!=first){
            t.next= t.next.next;
            t.next.prev = t;
        }
        if(size>0){
            size--;
        }
        else {
            size = 0;
        }
    }

    public int size(){
        return size;
    }

    @Override
    public LinkedListTw.Iterator iterator() {
        return new LinkedListTw.Iterator();
    }



    class Iterator implements java.util.Iterator{

        Node startElem = first;
        @Override
        public boolean hasNext() {

            if(startElem.next!=first){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public Floor next() {
            startElem = startElem.next;
            return startElem.item;
        }
    }
}