package erd.routedeviation.demo;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import erd.routedeviation.utils.ecart_route_func;
import erd.routedeviation.enums.projection_types;

public class maindemo {

	/*
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		// Creation window of project
		mainwindow Route_window = new mainwindow();
		
		// Default Window Parameters
		int width=1000, height=500;
		
		Route_window.setTitle("Calcul/Démonstration Route"); //
		Route_window.setSize(width,height); //taille fenetre par defaut
		Route_window.setLocationRelativeTo(null); //centrage de la fenetre par rapport a l'ecran
		
		// TODO B1 - Creation tableau Haut Gauche
		// TODO B2 - Creation Canvas Haut droite
		// TODO B3 - Creation labels/lineEdit bas gauche = entree pos porteur
		// TODO B4 - Creation labels/lineEdit bas droite = sortie resultat ecart route et next waypoint
		
//		Scanner scan = new Scanner(System.in);
//		System.out.print("Choose projection \"MERCATOR\" or \"MILLER\" : ");
//		projection_types type_P = projection_types.MERCATOR;
//		String type = scan.nextLine();
//		projection_types p=null;
//		if (type=="MERCATOR" || type=="mercator") proj=projection_types.MERCATOR;
//		else if (type=="MILLER" || type=="miller") proj=projection_types.MILLER;
//		//else System.out.print("NOOB, ciao !");
		
		projection_types proj=projection_types.MERCATOR;
				
		//partie Test
		Map<String, ArrayList<String>> datas_wp = ecart_route_func.get_wp_from_csv_file("C:\\Users\\Pierre.MATTIOLI\\Desktop\\data.txt");

		ArrayList<ArrayList<String>> wp_values = new ArrayList<ArrayList<String>>(datas_wp.values());
		ArrayList<String> wp_keys = new ArrayList<String>(datas_wp.keySet());
		
		@SuppressWarnings("rawtypes")
		Vector<Vector> datas_tab = new Vector<Vector>();
        
		ArrayList<Point2D.Double> list_points = new ArrayList<Point2D.Double>();
		for (int i=0; i<datas_wp.size();i++)
		{
			Vector<String> data_row = new Vector<String>();
			data_row.addElement(wp_keys.get(i));
			data_row.addElement(wp_values.get(i).get(0));
			data_row.addElement(wp_values.get(i).get(1));
			
			double _x=ecart_route_func.convert_DMS_to_lat_degree(wp_values.get(i).get(0));
			double _y=ecart_route_func.convert_DMS_to_lon_degree(wp_values.get(i).get(1));
			
			list_points.add(ecart_route_func.convert_lat_lon_to_2DPoint(_x, _y, width, height));
			
			datas_tab.addElement(data_row);
		}
		
		//En-têtes pour JTable 
		 Vector<String> columnNames = new Vector<String>();
		    columnNames.addElement("NAME");
		    columnNames.addElement("LAT");
		    columnNames.addElement("LON");
         
        Route_window.table.setModel(new DefaultTableModel(datas_tab,columnNames));
		
//		double lat_porteur = ecart_route_func.convert_DMS_to_lat_degree("39°27.51'N");
//		double lon_porteur = ecart_route_func.convert_DMS_to_lon_degree("007°01.36'E");
		
		double lat_porteur = ecart_route_func.convert_DMS_to_lat_degree("39°27.51'N");
		double lon_porteur = ecart_route_func.convert_DMS_to_lon_degree("007°01.36'E");
		
//		double lat_porteur = ecart_route_func.convert_DMS_to_lat_degree("40°43.12'N");
//		double lon_porteur = ecart_route_func.convert_DMS_to_lon_degree("006°52.33'E");
		Point2D.Double porteur_point = ecart_route_func.convert_lat_lon_to_2DPoint(lat_porteur, lon_porteur, width, height);
		
		for (Point2D.Double p : list_points)
		{
			System.out.println(p.x + " " + p.y);
		}
		
		System.out.println(porteur_point);
		//System.out.println(184.5775*(1000/360));
		
		System.out.println("Le next WP est " + wp_keys.get(ecart_route_func.get_indice_next_waypoint(porteur_point, list_points)));
		Route_window.txtAnswer.setText(wp_keys.get(ecart_route_func.get_indice_next_waypoint(porteur_point, list_points)));
	}

}
