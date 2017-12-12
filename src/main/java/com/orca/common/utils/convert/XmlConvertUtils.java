package com.orca.common.utils.convert;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * XML工具类
 * @author master.yang
 */
public class XmlConvertUtils extends AbstractCollectionConverter {

    public XmlConvertUtils(Mapper mapper) {
        super(mapper);
    }

    /** 
     * @see com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter#canConvert(Class)
     */
    @Override
    public boolean canConvert(Class type) {
        return type.equals(HashMap.class);
    }

    /** 
     * @see com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter#marshal(Object, com.thoughtworks.xstream.io.HierarchicalStreamWriter, com.thoughtworks.xstream.converters.MarshallingContext)
     */
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map<String, String> map = (Map<String, String>) source;
        Entry<String, String> entry = null;
        for (Iterator<Entry<String, String>> iterator = map.entrySet().iterator(); iterator
            .hasNext();) {
            entry = (Entry<String, String>) iterator.next();
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, entry.getKey().toString(),
                Entry.class);
            writer.setValue(safeGetEntryValue(entry));
            writer.endNode();
        }
    }

    private String safeGetEntryValue(Entry<String, String> entry) {
        if (entry == null) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isBlank(entry.getValue())) {
            return StringUtils.EMPTY;
        }
        return entry.getValue().toString();
    }

    /** 
     * @see com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter#unmarshal(com.thoughtworks.xstream.io.HierarchicalStreamReader, com.thoughtworks.xstream.converters.UnmarshallingContext)
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        throw new UnsupportedOperationException("不支持的操作");
    }
}
