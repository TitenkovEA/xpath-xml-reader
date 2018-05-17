import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class XmlReader {
    private static final String XPATH_TAG_ATTR_WITH_VALUE = "//%s[@%s='%s']";
    private static final String XPATH_TAG_ATTR = "//%s[@%s]";
    private static final String XPATH_ATTR_WITH_VALUE = "//*[@%s='%s']";
    private static final String XPATH_TAG = "//%s";
    private static final String XPATH_ATTR = "//*[@%s]";
    private static final String XPATH_ROOT = ".";
    private static final String XPATH_ROOT_ATTR = "@%s";

    private Configurations configs;
    private XMLConfiguration xml;

    XmlReader(String filePath) throws ConfigurationException {
        configs = new Configurations();
        xml = configs.xml(filePath);
        xml.setExpressionEngine(new XPathExpressionEngine());
    }

    XmlReader(File file) throws ConfigurationException {
        configs = new Configurations();
        xml = configs.xml(file);
        xml.setExpressionEngine(new XPathExpressionEngine());
    }

    public SelectedNode selectRootNode() {
        return new SelectedNode(xml.configurationAt(XPATH_ROOT));
    }

    public SelectedNode selectNodeByXpath(String xpath) {
        return new SelectedNode(xml.configurationAt(xpath));
    }

    public SelectedNode selectNodeByTag(String tag) {
        return new SelectedNode(xml.configurationAt(String.format(XPATH_TAG, tag)));
    }

    public SelectedNode selectNodeByAttr(String attrName, String attrValue) {
        return new SelectedNode(xml.configurationAt(String.format(XPATH_ATTR_WITH_VALUE, attrName, attrValue)));
    }

    public SelectedNode selectNodeByAttr(String attrName) {
        return new SelectedNode(xml.configurationAt(String.format(XPATH_ATTR, attrName)));
    }

    public SelectedNode selectNodeByTagAndAttr(String tag, String attrName) {
        return new SelectedNode(xml.configurationAt(String.format(XPATH_TAG_ATTR, tag, attrName)));
    }

    public SelectedNode selectNodeByTagAndAttr(String tag, String attrName, String attrValue) {
        return new SelectedNode(xml.configurationAt(String.format(XPATH_TAG_ATTR_WITH_VALUE, tag, attrName, attrValue)));
    }

    public SelectedNodes selectNodesByXpath(String xpath) {
        return new SelectedNodes(xml.configurationsAt(xpath));
    }

    public SelectedNodes selectNodesByTag(String tag) {
        return new SelectedNodes(xml.configurationsAt(String.format(XPATH_TAG, tag)));
    }

    public SelectedNodes selectNodesByAttr(String attrName, String attrValue) {
        return new SelectedNodes(xml.configurationsAt(String.format(XPATH_ATTR_WITH_VALUE, attrName, attrValue)));
    }

    public SelectedNodes selectNodesByAttr(String attrName) {
        return new SelectedNodes(xml.configurationsAt(String.format(XPATH_ATTR, attrName)));
    }

    public SelectedNodes selectNodesByTagAndAttr(String tag, String attrName) {
        return new SelectedNodes(xml.configurationsAt(String.format(XPATH_TAG_ATTR, tag, attrName)));
    }

    public SelectedNodes selectNodesByTagAndAttr(String tag, String attrName, String attrValue) {
        return new SelectedNodes(xml.configurationsAt(String.format(XPATH_TAG_ATTR_WITH_VALUE, tag, attrName, attrValue)));
    }

    public class SelectedNode {

        private HierarchicalConfiguration<ImmutableNode> selectedNode;

        private SelectedNode(HierarchicalConfiguration<ImmutableNode> node) {
            selectedNode = node;
        }

        public SelectedNode selectNodeByXpath(String xpath) {
            selectedNode = selectedNode.configurationAt(xpath);
            return this;
        }

        public SelectedNode selectNodeByTag(String tag) {
            selectedNode = selectedNode.configurationAt(String.format(XPATH_TAG, tag));
            return this;
        }

        public SelectedNode selectNodeByAttr(String attrName, String attrValue) {
            selectedNode = selectedNode.configurationAt(String.format(XPATH_ATTR_WITH_VALUE, attrName, attrValue));
            return this;
        }

        public SelectedNode selectNodeByAttr(String attrName) {
            selectedNode = selectedNode.configurationAt(String.format(XPATH_ATTR, attrName));
            return this;
        }

        public SelectedNode selectNodeByTagAndAttr(String tag, String attrName) {
            selectedNode = selectedNode.configurationAt(String.format(XPATH_TAG_ATTR, tag, attrName));
            return this;
        }

        public SelectedNode selectNodeByTagAndAttr(String tag, String attrName, String attrValue) {
            selectedNode = selectedNode.configurationAt(String.format(XPATH_TAG_ATTR_WITH_VALUE, tag, attrName, attrValue));
            return this;
        }

        public SelectedNodes selectNodesByXpath(String xpath) {
            return new SelectedNodes(selectedNode.configurationsAt(xpath));
        }

        public SelectedNodes selectNodesByTag(String tag) {
            return new SelectedNodes(selectedNode.configurationsAt(String.format(XPATH_TAG, tag)));
        }

        public SelectedNodes selectNodesByAttr(String attrName, String attrValue) {
            return new SelectedNodes(selectedNode.configurationsAt(String.format(XPATH_ATTR_WITH_VALUE, attrName, attrValue)));
        }

        public SelectedNodes selectNodesByAttr(String attrName) {
            return new SelectedNodes(selectedNode.configurationsAt(String.format(XPATH_ATTR, attrName)));
        }

        public SelectedNodes selectNodesByTagAndAttr(String tag, String attrName) {
            return new SelectedNodes(selectedNode.configurationsAt(String.format(XPATH_TAG_ATTR, tag, attrName)));
        }

        public SelectedNodes selectNodesByTagAndAttr(String tag, String attrName, String attrValue) {
            return new SelectedNodes(selectedNode.configurationsAt(String.format(XPATH_TAG_ATTR_WITH_VALUE, tag, attrName, attrValue)));
        }

        public boolean getBooleanAttr(String attr) {
            return selectedNode.getBoolean(String.format(XPATH_ROOT_ATTR, attr));
        }

        public byte getByteAttr(String attr) {
            return selectedNode.getByte(String.format(XPATH_ROOT_ATTR, attr));
        }

        public short getShortAttr(String attr) {
            return selectedNode.getShort(String.format(XPATH_ROOT_ATTR, attr));
        }

        public int getIntAttr(String attr) {
            return selectedNode.getInt(String.format(XPATH_ROOT_ATTR, attr));
        }

        public long getLongAttr(String attr) {
            return selectedNode.getLong(String.format(XPATH_ROOT_ATTR, attr));
        }

        public float getFloatAttr(String attr) {
            return selectedNode.getFloat(String.format(XPATH_ROOT_ATTR, attr));
        }

        public double getDoubleAttr(String attr) {
            return selectedNode.getDouble(String.format(XPATH_ROOT_ATTR, attr));
        }

        public String getStringAttr(String attr) {
            return selectedNode.getString(String.format(XPATH_ROOT_ATTR, attr));
        }

        public BigDecimal getBigDecimalAttr(String attr) {
            return selectedNode.getBigDecimal(String.format(XPATH_ROOT_ATTR, attr));
        }

        public boolean getBoolean() {
            return selectedNode.getBoolean(XPATH_ROOT);
        }

        public byte getByte() {
            return selectedNode.getByte(XPATH_ROOT);
        }

        public short getShort() {
            return selectedNode.getShort(XPATH_ROOT);
        }

        public int getInt() {
            return selectedNode.getInt(XPATH_ROOT);
        }

        public long getLong() {
            return selectedNode.getLong(XPATH_ROOT);
        }

        public float getFloat() {
            return selectedNode.getFloat(XPATH_ROOT);
        }

        public double getDouble() {
            return selectedNode.getDouble(XPATH_ROOT);
        }

        public String getString() {
            return selectedNode.getString(XPATH_ROOT);
        }

        public BigDecimal getBigDecimal() {
            return selectedNode.getBigDecimal(XPATH_ROOT);
        }
    }

    public class SelectedNodes {

        private List<HierarchicalConfiguration<ImmutableNode>> selectedNodes;

        private SelectedNodes(List<HierarchicalConfiguration<ImmutableNode>> nodes) {
            selectedNodes = nodes;
        }

        public List<SelectedNode> getSelectedNodeList() {
            return selectedNodes.stream()
                    .map(SelectedNode::new)
                    .collect(Collectors.toList());
        }

        public List<Boolean> getBooleanAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getBoolean(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Byte> getByteAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getByte(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Short> getShortAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getShort(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Integer> getIntAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getInt(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Long> getLongAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getLong(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Float> getFloatAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getFloat(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Double> getDoubleAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getDouble(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<String> getStringAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getString(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<BigDecimal> getBigDecimalAttrList(String attr) {
            return selectedNodes.stream()
                    .map(node -> node.getBigDecimal(String.format(XPATH_ROOT_ATTR, attr)))
                    .collect(Collectors.toList());
        }

        public List<Boolean> getBooleanList() {
            return selectedNodes.stream()
                    .map(node -> node.getBoolean(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<Byte> getByteList() {
            return selectedNodes.stream()
                    .map(node -> node.getByte(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<Short> getShortList() {
            return selectedNodes.stream()
                    .map(node -> node.getShort(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<Integer> getIntList() {
            return selectedNodes.stream()
                    .map(node -> node.getInt(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<Long> getLongList() {
            return selectedNodes.stream()
                    .map(node -> node.getLong(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<Float> getFloatList() {
            return selectedNodes.stream()
                    .map(node -> node.getFloat(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<Double> getDoubleList() {
            return selectedNodes.stream()
                    .map(node -> node.getDouble(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<String> getStringList() {
            return selectedNodes.stream()
                    .map(node -> node.getString(XPATH_ROOT))
                    .collect(Collectors.toList());
        }

        public List<BigDecimal> getBigDecimalList() {
            return selectedNodes.stream()
                    .map(node -> node.getBigDecimal(XPATH_ROOT))
                    .collect(Collectors.toList());
        }
    }
}
