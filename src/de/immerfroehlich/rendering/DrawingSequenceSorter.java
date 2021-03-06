package de.immerfroehlich.rendering;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import de.immerfroehlich.model.Drawable;

final class DrawingSequenceSorter {
	
	private class DrawableComparator implements Comparator<Drawable> {
		
		@Override
		public int compare(Drawable o1, Drawable o2) {
			Vector2f pos1 = converter.convertToArrayField(o1.getPosition());
			Vector2f pos2= converter.convertToArrayField(o2.getPosition());
			int x1 = (int) pos1.x;
			int y1 = (int) pos1.y;
			int x2 = (int) pos2.x;
			int y2 = (int) pos2.y;
			
			//same layer
			if (o1.getLayer() == o2.getLayer()) {
				//same row
				if(y1 == y2) {
					if(x1 < x2) return -1;
					else if (x1 > x2) return 1;
					else if (x1 == x2) return 0;
				}
				//different row
				else {
					if(y1 < y2) return -1;
					else if (y1 > y2) return 1;
				}
			}
			//different layer
			else {
				if(o1.getLayer() < o2.getLayer()) return -1;
				else if (o1.getLayer() > o2.getLayer()) return 1;
			}
			
			throw new IllegalStateException("Not all possibilities covered. Rethink it.");
			
		}
		
	}
	
	private DrawableComparator comparator;
	
	private GridConverter converter;
	
	public DrawingSequenceSorter(GridConverter converter) {
		comparator = new DrawableComparator();
		this.converter = converter;
	}
	
	
	public void sort(List<Drawable> drawables) {
		Collections.sort(drawables, comparator);
	}

}
