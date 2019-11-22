package buildings.List;


import buildings.Exceptions.SpaceIndexOutOfBoundsException;

import buildings.Spaces.Office;
import buildings.interfaces.Space;

import java.io.Serializable;


public class LinkedList implements Iterable, Serializable {
    class Node implements Serializable {
        Node next;
        Space item;
    }
    private Node first;
    private int size = 0;

    public LinkedList(Space[] items){
        first = new Node();
        first.next =first;
        for (var v: items) {
            addLast(v);
        }
    }
    public LinkedList(int a){
        for (int i = 0; i < a; i++) {
            addLast(null);
        }
    }
    void addFirst(Space item)
    {
        Node a = new Node();
        a.item = item;
            a.next = first;
            first = a;

        size++;
    }
    public Space[] toArray() {
        var resultArray =  new Space[this.size];
        int index = 0;
        for (Node link = this.first.next; link != first; link = link.next) {
            resultArray[index++] = link.item;
        }

        return resultArray;
    }
    public Space get(int i){
        Node n = first.next.next;
        if(i == 0){
            return first.next.item;
        }
        for(int j = 1; n!=first && j <i;j++){
            n = n.next;
        }
        return n.item;
    }

    public void set(int i,Space e){
        if(i<size){
            var n = first.next;
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
    void addLast(Space item) {
        var a = new Node();
        a.item = item;
        var v = first;
        while (v.next!=first){
            v = v.next;
        }
        v.next = a;
        a.next = first;
        size++;
    }

   public Space getFirst()
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
        }
        if(size>0){
            size--;
        }
        else {
            size = 0;
        }
    }

    public int getSize(){
        return size;
    }

    @Override
    public Iterator iterator() {
        return new Iterator();
    }



    class Iterator implements java.util.Iterator{

        Node startElem = first;
        @Override
        public boolean hasNext() {

            if(startElem.next!=null){
                return true;
            }else {
                return false;
            }
        }

        @Override
        public Space next() {
            startElem = startElem.next;
            return startElem.item;
        }
    }
}