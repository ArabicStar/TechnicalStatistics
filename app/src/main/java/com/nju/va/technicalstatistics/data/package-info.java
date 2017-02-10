/**
 * Package of interfaces for persistence.<br/>
 * If not particularly indicated, following operations will be supported:
 * <table>
 * <th style="width:5%">Method pattern</th>
 * <th style="width:15%">Usage</th>
 * <th style="width:15%">Parameters</th>
 * <th style="width:15%">Return</th>
 * <th style="width:40%">Remark</th>
 * <tr>
 * <td><code>save(T):boolean</code></td>
 * <td>Save an entity</td>
 * <td>To be saved entity</td>
 * <td>If all operations succeed</td>
 * <td>
 * Insertion doesn't check if any existed entities logically equal to the argument. In other
 * words, the method always saves the argument as a new record.</td>
 * </tr>
 * <tr>
 * <td><code>delete(int):boolean</code></td>
 * <td>Delete an entity</td>
 * <td>Entity id</td>
 * <td>If all operations succeed</td>
 * <td>Deletion will not actually delete records, just set the appointed record invalid
 * instead.</td>
 * </tr>
 * <tr>
 * <td><code>update(T):boolean</code></td>
 * <td>Update an entity</td>
 * <td>To be updated entity</td>
 * <td>If all operations succeed</td>
 * <td>Invalid entities will be skipped</td>
 * </tr>
 * <tr>
 * <td><code>find(int,boolean):T</code></td>
 * <td>Find entity by id</td>
 * <td>Entity id; if ignore invalid entities</td>
 * <td>Entity found, null if not found</td>
 * <td></td>
 * </tr>
 * <tr>
 * <td><code>find(String):{@link java.util.List}&lt;T&gt;</code></td>
 * <td>Find entities by key word in name</td>
 * <td>Key word in name</td>
 * <td>{@link java.util.List} of entities found, empty List if not found </td>
 * <td>Invalid entities will be ignored.</td>
 * </tr>
 * </table>
 * If not particularly indicated, following contracts will be carried:
 * <ul>
 * <li>Operations will be applied to relevant entities simultaneously. </li>
 * <li>Methods return <code>true</code> <i>iff.</i> all operations succeed. Any failure will cause
 * all operations being rolled back.</li>
 * <li>Methods return <code>false</code>, besides the failure condition mentioned before, if
 * operations will not result in any sql excution, <i>e.g.</i> the argument
 * is <code>null</code>.</li>
 * </ul>
 */
package com.nju.va.technicalstatistics.data;