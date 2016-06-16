package app_ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.MainClass;
import net.miginfocom.swing.MigLayout;

public class MainWindow 
{
	public static String version = "2.0.0.5 : Log area operational";
	
	private JFrame frmRegressionSuiteV;
	
	public static JCheckBox PELExistingCustomer;
	public static JCheckBox PDLExistingCustomer;
	public static JCheckBox PELPDLNewCustomer;
	public static JCheckBox PCLExistingCustomer;
	public static JCheckBox PCLNewCustomer;
	public static JCheckBox PCLNewCustomerwithCreditLimit;
	public static JCheckBox PTVExisting;
	public static JCheckBox PELAddMorePDL;
	public static JCheckBox PELAddMorePCL;
	public static JCheckBox PTIExisting;
	public static JCheckBox PCLComverseExistingCustomer;
	public static JCheckBox BatchPayment;
	public static JCheckBox CreateRefundDepositReason;
	public static JCheckBox VerifyCashdrawer;
	public static JCheckBox FibreRouting;
	
	public static JComboBox<String> AUTSelection;
	public static JLabel PELExistingCustomerStatus;
	public static JLabel PDLExistingCustomerStatus;
	public static JLabel PELPDLNewCustomerStatus;
	public static JLabel PCLExistingCustomerStatus;
	public static JLabel PCLNewCustomerStatus;
	public static JLabel PCLNewCustomerwithCreditLimitStatus;
	public static JLabel PTVExistingStatus;
	public static JLabel PELAddMorePDLStatus;
	public static JLabel PELAddMorePCLStatus;
	public static JLabel PTIExistingStatus;
	public static JLabel PCLComverseExistingCustomerStatus;
	public static JLabel BatchPaymentStatus;
	public static JLabel FibreRoutingStatus;
	public static JLabel CreateRefundDepositReasonStatus;
	public static JLabel VerifyCashdrawerStatus;
	private JPanel panel;
	private JButton btnNewButton;
	private JScrollPane scrollPane_tcTable;
	private JTable table;
	private JScrollPane scrollPane_UIlog;
	private static JTextArea automationUILog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmRegressionSuiteV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() 
	{
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegressionSuiteV = new JFrame();
		frmRegressionSuiteV.setTitle("Regression Suite V2 " + version);;
		frmRegressionSuiteV.setBounds(100, 100, 766, 560);
		frmRegressionSuiteV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegressionSuiteV.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmRegressionSuiteV.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		JPanel Regression_Panel = new JPanel();
		tabbedPane.addTab("Regression Suite", null, Regression_Panel, null);
		Regression_Panel.setLayout(new MigLayout("", "[][][grow][][][::100px][][grow]", "[][][][][][][][][][][][][][][][][]"));
		
		AUTSelection = new JComboBox<String>();
		AUTSelection.setModel(new DefaultComboBoxModel<String>(new String[] {"S06", "S10"}));
		Regression_Panel.add(AUTSelection, "cell 2 0,growx");
		
		JButton ExecuteButton = new JButton("Execute");
		ExecuteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				//Execute Button Mouse Released Method
				MainClass suite = new MainClass();
		        Thread automation = new Thread(suite);
		        
		        automation.start();
			}
		});
		Regression_Panel.add(ExecuteButton, "cell 3 0");
		
		JButton ClearButton = new JButton("Clear");
		Regression_Panel.add(ClearButton, "cell 4 0");
		
		PELExistingCustomer = new JCheckBox("PEL Existing Customer");
		Regression_Panel.add(PELExistingCustomer, "cell 0 1");
		
		PELExistingCustomerStatus = new JLabel("");
		PELExistingCustomerStatus.setLabelFor(PELExistingCustomer);
		PELExistingCustomerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PELExistingCustomerStatus, "cell 2 1");
		
		PDLExistingCustomer = new JCheckBox("PDL Existing Customer");
		Regression_Panel.add(PDLExistingCustomer, "cell 0 2");
		
		PDLExistingCustomerStatus = new JLabel("");
		PDLExistingCustomerStatus.setLabelFor(PDLExistingCustomer);
		PDLExistingCustomerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PDLExistingCustomerStatus, "cell 2 2");
		
		PELPDLNewCustomer = new JCheckBox("PEL + PDL New Customer");
		Regression_Panel.add(PELPDLNewCustomer, "cell 0 3");
		
		PELPDLNewCustomerStatus = new JLabel("");
		PELPDLNewCustomerStatus.setLabelFor(PELPDLNewCustomer);
		PELPDLNewCustomerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PELPDLNewCustomerStatus, "cell 2 3");
		
		PCLExistingCustomer = new JCheckBox("PCL Existing Customer");
		Regression_Panel.add(PCLExistingCustomer, "cell 0 4");
		
		PCLExistingCustomerStatus = new JLabel("");
		PCLExistingCustomerStatus.setLabelFor(PCLExistingCustomerStatus);
		PCLExistingCustomerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PCLExistingCustomerStatus, "cell 2 4");
		
		PCLNewCustomer = new JCheckBox("PCL New Customer");
		Regression_Panel.add(PCLNewCustomer, "cell 0 5");
		
		PCLNewCustomerStatus = new JLabel("");
		PCLNewCustomerStatus.setLabelFor(PCLNewCustomer);
		PCLNewCustomerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PCLNewCustomerStatus, "cell 2 5");
		
		PCLNewCustomerwithCreditLimit = new JCheckBox("New Customer - Credit Limit");
		Regression_Panel.add(PCLNewCustomerwithCreditLimit, "cell 0 6");
		
		PCLNewCustomerwithCreditLimitStatus = new JLabel("");
		PCLNewCustomerwithCreditLimitStatus.setLabelFor(PCLNewCustomerwithCreditLimit);
		PCLNewCustomerwithCreditLimitStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PCLNewCustomerwithCreditLimitStatus, "cell 2 6");
		
		PTVExisting = new JCheckBox("PTV Existing Customer");
		Regression_Panel.add(PTVExisting, "cell 0 7");
		
		PTVExistingStatus = new JLabel("");
		PTVExistingStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PTVExistingStatus, "cell 2 7");
		
		PELAddMorePDL = new JCheckBox("AddMore PEL + PDL");
		Regression_Panel.add(PELAddMorePDL, "cell 0 8");
		
		PELAddMorePDLStatus = new JLabel("");
		PELAddMorePDLStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PELAddMorePDLStatus, "cell 2 8");
		
		PELAddMorePCL = new JCheckBox("AddMore PEL + PCL");
		Regression_Panel.add(PELAddMorePCL, "cell 0 9");
		
		PELAddMorePCLStatus = new JLabel("");
		PELAddMorePCLStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PELAddMorePCLStatus, "cell 2 9");
		
		PTIExisting = new JCheckBox("PTI Existing Customer");
		Regression_Panel.add(PTIExisting, "cell 0 10");
		
		PTIExistingStatus = new JLabel("");
		PTIExistingStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PTIExistingStatus, "cell 2 10");
		
		PCLComverseExistingCustomer = new JCheckBox("Comverse Existing Customer");
		Regression_Panel.add(PCLComverseExistingCustomer, "cell 0 11");
		
		PCLComverseExistingCustomerStatus = new JLabel("");
		PCLComverseExistingCustomerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(PCLComverseExistingCustomerStatus, "cell 2 11");
		
		BatchPayment = new JCheckBox("Batch Payment");
		Regression_Panel.add(BatchPayment, "cell 0 12");
		
		BatchPaymentStatus = new JLabel("");
		BatchPaymentStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(BatchPaymentStatus, "cell 2 12");
		
		FibreRouting = new JCheckBox("Fiber Route");
		Regression_Panel.add(FibreRouting, "cell 0 13");
		
		FibreRoutingStatus = new JLabel("");
		FibreRoutingStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(FibreRoutingStatus, "cell 2 13");
		
		CreateRefundDepositReason = new JCheckBox("Create and Refund Deposit");
		Regression_Panel.add(CreateRefundDepositReason, "cell 0 14");
		
		CreateRefundDepositReasonStatus = new JLabel("");
		CreateRefundDepositReasonStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(CreateRefundDepositReasonStatus, "cell 2 14");
		
		VerifyCashdrawer = new JCheckBox("Verify Cashdrawer");
		Regression_Panel.add(VerifyCashdrawer, "cell 0 15");
		
		VerifyCashdrawerStatus = new JLabel("");
		VerifyCashdrawerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Regression_Panel.add(VerifyCashdrawerStatus, "cell 2 15");
		
		JPanel Sanity_Panel = new JPanel();
		tabbedPane.addTab("Sanity Suite", null, Sanity_Panel, null);
		
		panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(new MigLayout("", "[grow][][][][][][][grow]", "[][::300px][grow]"));
		
		btnNewButton = new JButton("New button");
		panel.add(btnNewButton, "cell 2 0");
		
		scrollPane_tcTable = new JScrollPane();
		panel.add(scrollPane_tcTable, "cell 0 1 8 1,grow");
		
		table = new JTable();
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(new DefaultTableModel(new Object[][] 
			{
				{null,"PEL Existing Customer",null,null},
				{null,"PDL Existing Customer",null,null},
				{null,"PEL + PDL New Customer",null,null},
				{null,"PCL Existing Customer",null,null},
				{null,"PCL New Customer",null,null},
				{null,"New Customer - Credit Limit",null,null},
				{null,"PTV Existing Customer",null,null},
				{null,"AddMore PEL + PDL",null,null},
				{null,"AddMore PEL + PCL",null,null},
				{null,"PTI Existing Customer",null,null},
				{null,"Comverse Existing Customer",null,null},
				{null,"Batch Payment",null,null},
				{null,"Fiber Route",null,null},
				{null,"Create and Refund Deposit",null,null},
				{null,"Verify Cashdrawer",null,null}
			},
			new String[] {
				"Selection", "Test Case", "Status", "Time Taken"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(53);
		table.getColumnModel().getColumn(1).setPreferredWidth(256);
		scrollPane_tcTable.setViewportView(table);
		
		scrollPane_UIlog = new JScrollPane();
		panel.add(scrollPane_UIlog, "cell 0 2 8 1,grow");
		
		automationUILog = new JTextArea();
		automationUILog.setForeground(Color.BLUE);
		automationUILog.setEditable(false);
		automationUILog.setSize(automationUILog.getSize());
		scrollPane_UIlog.setViewportView(automationUILog);
		
		scrollPane_UIlog.setPreferredSize(automationUILog.getPreferredSize());
		
		JMenuBar menuBar = new JMenuBar();
		frmRegressionSuiteV.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmData = new JMenuItem("Data");
		mnEdit.add(mntmData);
		
		JMenuItem mntmConfiguration = new JMenuItem("Configuration");
		mnEdit.add(mntmConfiguration);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
	
	public static void writetoUIconsole(String message)
	{
		MainWindow.automationUILog.append(message);
		MainWindow.automationUILog.revalidate();
		automationUILog.setCaretPosition(automationUILog.getDocument().getLength());
	}
}
