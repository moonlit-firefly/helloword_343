package com.iweb.DAO;

import java.util.List;

/**
 * @author 陈郅治
 * @date 2023/3/31  16:45
 **/
public interface EntityDAO<T> {

    /**添加一个新对象进入数据库对应表
     * @param bean 待插入的数据对象
     * @return
     */
    boolean add(T bean);

    /**删除表中对应数据
     * @param bean 待删除的数据对象
     * @return
     */
    boolean delete(T bean);

    /**更新表中数据
     * @param bean 待更新成的数据对象
     * @return
     */
    boolean update(T bean);

    /**根据id在表中获取数据对象
     * @param id 待获取的数据对象id
     * @return
     */
    T get(int id);

    /**获取表所有数据对象集合
     * @return 表所有数据对象集合
     */
    List<T> list();

    /**分页获取表所有数据对象集合
     * @param start 分页起始id
     * @param count 一页的数据数目
     * @return
     */
    List<T> list(int start,int count);
}
