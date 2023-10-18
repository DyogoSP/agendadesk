package com.dyogo.agendadesk;

import java.awt.EventQueue;

import com.dyogo.agendadesk.ui.UiPrincipal;

public class AgendaDesk {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiPrincipal ui = new UiPrincipal();
					ui.mostrar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
