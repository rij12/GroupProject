package uk.ac.aber.cs221.group16.controller;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class JListListener implements ListDataListener{

	@Override
	public void intervalAdded(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		e.getSource();		
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		// TODO Auto-generated method stub
		
	}
}