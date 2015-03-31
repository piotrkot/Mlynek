/**
 * Copyright (c) 2015. piotrkot
 */

package com.piotrkot.mock;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Mock of multivalued map.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class McMultivalueMap<K, V> implements MultivaluedMap<K, V> {
    /**
     * Backed up hash map.
     */
    private final transient Map<K, List<V>> map;

    /**
     * Class constructor.
     */
    public McMultivalueMap() {
        this.map = new HashMap<>(0);
    }
    @Override
    public void putSingle(final K key, final V val) {
        this.map.put(key, Lists.newArrayList(val));
    }

    @Override
    public void add(final K key, final V val) {
        this.map.get(key).add(val);
    }

    @Override
    public V getFirst(final K key) {
        return this.map.get(key).get(0);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public List<V> get(final Object key) {
        return this.map.get(key);
    }

    @Override
    public List<V> put(final K key, final List<V> value) {
        return this.map.put(key, value);
    }

    @Override
    public List<V> remove(final Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(final Map<? extends K, ? extends List<V>> mapp) {
        this.map.putAll(mapp);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<List<V>> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<K, List<V>>> entrySet() {
        return this.map.entrySet();
    }
}
