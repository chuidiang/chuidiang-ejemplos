package chuidiang.reconocedor_idioma;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.CharArrayReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;


public class PruebaCorrelacionIdiomas extends JApplet
{
    private static final int ALTO_VENTANA = 600;

	private static final int ANCHO_VENTANA = 500;

    private static final long serialVersionUID = 5156346059940499690L;

    private JTextArea areaDeTextoAAnalizar;

    private CorreladorIdiomas correlador;

    private JTextArea areaResultados;

    public void init()
    {
        anhadeComponentes(this);
        inicializaCorrelador();
    }

    public static void main(String[] args)
    {
        PruebaCorrelacionIdiomas prueba = new PruebaCorrelacionIdiomas();
        prueba.creaYVisualizaVentana();
    }

    public void creaYVisualizaVentana()
    {
        JFrame v = new JFrame();
        v.setTitle("Analizador Idiomas");
        anhadeComponentes(v.getContentPane());
        inicializaCorrelador();
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        v.setSize(ANCHO_VENTANA, ALTO_VENTANA);
        v.setVisible(true);
    }

    private void anhadeComponentes(Container contenedor)
    {
        contenedor.setLayout(new GridBagLayout());

        anhadeTextAreaParaTextoAnalizado(contenedor);
        anhadeBotonCorrela(contenedor);
        anhadeBotonGeneraTexto(contenedor);
        anhadeBotonLimpiaTexto(contenedor);
        anhadeTextAreaParaResultados(contenedor);
    }

	private void anhadeTextAreaParaResultados(Container contenedor )
	{
		areaResultados = new JTextArea();
        areaResultados.setLineWrap(true);
        areaResultados.setWrapStyleWord(true);
        areaResultados.setEditable(false);
        areaResultados.setColumns(40);
        areaResultados.invalidate();

        JScrollPane scrollLista = new JScrollPane(areaResultados);
        scrollLista.setBorder(new TitledBorder("Resultados"));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        contenedor.add(scrollLista, constraints);
	}

	private void anhadeTextAreaParaTextoAnalizado(Container contenedor) {
		areaDeTextoAAnalizar = new JTextArea();
        areaDeTextoAAnalizar.setLineWrap(true);
        areaDeTextoAAnalizar.setWrapStyleWord(true);
        areaDeTextoAAnalizar.setColumns(40);
        areaDeTextoAAnalizar.invalidate();

        JScrollPane scroll = new JScrollPane(areaDeTextoAAnalizar);
        scroll.setBorder(new TitledBorder("Escribe o copia aqu� un texto"));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        contenedor.add(scroll, constraints);
	}

	private void anhadeBotonLimpiaTexto(Container contenedor ) {
		GridBagConstraints constraints = new GridBagConstraints();
		JButton limpiaTexto = new JButton("Borrar Texto");
        limpiaTexto.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    areaDeTextoAAnalizar.setText("");
                }
            });
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.BOTH;
        contenedor.add(limpiaTexto, constraints);
	}

	private void anhadeBotonCorrela(Container contenedor ) {
		GridBagConstraints constraints = new GridBagConstraints();
		JButton botonCorrela = new JButton("Analizar idioma");
        botonCorrela.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    analizaTextoYExtraeResultados();
                }
            });

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.BOTH;
        contenedor.add(botonCorrela, constraints);
	}
	
	private void anhadeBotonGeneraTexto(Container contenedor ) {
		GridBagConstraints constraints = new GridBagConstraints();
		JButton botonCorrela = new JButton("Deja, ya escribo yo");
        botonCorrela.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    generaTexto();
                }
            });

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.BOTH;
        contenedor.add(botonCorrela, constraints);
	}

    protected void generaTexto() {
    	Enumeration<String> claves = idiomasCargados.keys();
    	String [] idiomas = new String [idiomasCargados.size()];
    	for (int i=0;i<idiomas.length;i++)
    	{
    		idiomas[i] = claves.nextElement();
    	}
    	int seleccionado = JOptionPane.showOptionDialog(
    			SwingUtilities.getWindowAncestor(this),
    			"Selecciona en qu� idioma genero el texto",
    			"Selecci�n de idioma",
    			JOptionPane.DEFAULT_OPTION,
    			JOptionPane.QUESTION_MESSAGE,
    			null,
    			idiomas,
    			0
    			);
    	
    	if(seleccionado <0)
    		return;
    	
    	GeneradorTexto generador = new GeneradorTexto(
    			idiomas[seleccionado],
    			new InputStreamReader (
    					getClass().getClassLoader().getResourceAsStream(
    							idiomasCargados.get(idiomas[seleccionado]))));
    	StringBuffer textoGenerado = generador.dameTexto(500);
    	areaDeTextoAAnalizar.setText("");
    	areaDeTextoAAnalizar.setText(textoGenerado.toString());
	}

    public static final Hashtable<String,String> idiomasCargados=new Hashtable<String, String>();
    {
    	idiomasCargados.put("Espa�ol","espanol.txt");
    	idiomasCargados.put("Catalan","catalan.txt");
    	idiomasCargados.put("Italiano","italiano.txt");
    	idiomasCargados.put("Bable","bable.txt");
    	idiomasCargados.put("Ingles","ingles.txt");
    	idiomasCargados.put("Rumano","rumano.txt");
    	idiomasCargados.put("Aleman","aleman.txt");
    	idiomasCargados.put("Frances","frances.txt");
    	idiomasCargados.put("Checo","checo.txt");
    	idiomasCargados.put("Euskera","euskera.txt");
    	idiomasCargados.put("xml","build.xml");
    	idiomasCargados.put("java","PruebaCorrelacionIdiomas.java");
    };
    
    private void inicializaCorrelador()
    {
        correlador = new CorreladorIdiomas();
	
        try
        {
        	Enumeration<String> claves = idiomasCargados.keys();
        	while (claves.hasMoreElements())
        	{
        		String idioma = claves.nextElement();
                ClassLoader classLoader = getClass().getClassLoader();
                correlador.addIdioma(
                    idioma,
                    new InputStreamReader(
                        classLoader.getResourceAsStream(idiomasCargados.get(idioma))));
        		
        	}
        }
        catch (Exception e)
        {
            areaResultados.setText("No se pueden cargar idiomas: " + e);
            System.err.println("Problemas al cargar idiomas");
        }
    }

    private void analizaTextoYExtraeResultados()
    {
        String texto = areaDeTextoAAnalizar.getText();
        CharArrayReader stream = new CharArrayReader(texto.toCharArray());
        PorcentajeCorrelacion[] porcentajes = correlador.analizaIdioma(stream);
        areaResultados.setText("");

        for (int i = porcentajes.length - 1; i >= 0; i--)
        {
            areaResultados.append(porcentajes[i] + "\n");
        }
    }
}
