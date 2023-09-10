package tn.esprit.templatespring.Controller;

import java.util.List;
import java.util.ArrayList;

import tn.esprit.templatespring.Entities.StructureHierarchique;

class StructureHierarchiqueNode {
    private StructureHierarchique structure;
    private List<StructureHierarchiqueNode> children;
    
    @Override
	public String toString() {
		return  structure.getLibelle()  + children ;
	}

	public StructureHierarchiqueNode(StructureHierarchique structure) {
        this.structure = structure;
        this.children = new ArrayList<>();
    }
    
    public StructureHierarchique getStructure() {
        return structure;
    }
    
    public List<StructureHierarchiqueNode> getChildren() {
        return children;
    }
    
    public void addChild(StructureHierarchiqueNode child) {
        children.add(child);
    }
}
