/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

/**
 *
 * @author David
 */
public enum Operation {
    LOGIN, 
    CREATE_ACCOUNT, 
    DEACTIVATE_ACCOUNT, 
    DISCONNECT,
    GET_ALL_PERFORMANCES,
    SAVE_RESERVATION,
    SAVE_RESERVATION_ITEMS,
    GET_MAX_INDEX,
    GET_ALL_RESERVATION_ITEMS,
    REMOVE_RESERVATION_ITEM,
    GET_ALL_RESERVATION_ITEMS_FOR_RESERVATION,
    REMOVE_RESERVATION
}
