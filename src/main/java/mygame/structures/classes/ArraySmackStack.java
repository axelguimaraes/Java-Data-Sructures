/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame.structures.classes;

import mygame.structures.interfaces.SmackStackADT;
import mygame.structures.interfaces.StackADT;

public class ArraySmackStack<T> extends ArrayStack<T>
        implements SmackStackADT<T> {

    /**
     * Removes the last stack element.
     *
     * @return the element to be removed.
     */
    @Override
    public T smack() {
        if (super.size() == 0) {
            return null;
        }
        
        StackADT<T> temp = new ArrayStack<>();
        
        while(super.size() > 1){
            temp.push(super.pop());
        }
        
        T removed = super.pop();
        
        while(temp.size() > 0){
            super.push(temp.pop());
        }
        
        return removed;
    }

}
