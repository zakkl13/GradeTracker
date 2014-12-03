package com.example.gradesapp;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * // -------------------------------------------------------------------------
/**
 *  This is our required Stack Implementation. Orginially we planned to use a
 *  Queue. Unfortunately, Parcel Objects only support generic Lists.
 *
 *  @author Jason
 *  @version Dec 2, 2014
 */
public class ArrayListStack
{
    private ArrayList<Assignment> list;

    //~ Constructors .........................................................
    /**
     * the constructor
     */
    public ArrayListStack()
    {
        list = new ArrayList<Assignment>();
    }


    //~ Methods ..............................................................
    /**
     * pushes the item to the top
     * @param item is an item
     */
    public void push(Assignment item)
    {
        list.remove(item);
        list.add(0, item);

    }

    /**
     * pops an item off the top
     */
    public void pop()
    {
        if (list.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            list.remove(0);
        }

    }

    /**
     * top method returns top
     * @return the top
     */
    public Assignment top()
    {
        if (list.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return list.get(0);
        }

    }

    /**
     * size
     * @return the size
     */
    public int size()
    {
        return list.size();
    }

    /**
     * indicates if the stack is empty
     * @return is empty
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    /**
     * gets the arrayList
     */
    public ArrayList<Assignment> getList()
    {
        return list;

    }
    /**
     * clears the list
     */
    public void clear()
    {
        list.clear();
    }

}
