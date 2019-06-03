import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 

public class Calculator extends JFrame implements ActionListener {    
    private final String[] KEYS = {"7","8","9","/","4","5","6","*","1","2","3","-","0","+/-",".","+","1/x","%","sqrt","="};  
    private final String[] COMMAND = { "Backspace","C" };  
    private JButton keys[] = new JButton[KEYS.length];   
    private JButton commands[] = new JButton[COMMAND.length];  
    private JTextField resultText = new JTextField("0"); 
    private JMenuBar mb1=new JMenuBar();
    private JMenu m1=new JMenu("�ļ�");
    private JMenu m2=new JMenu("����");
    
    private boolean firstDigit = true;   
    private double resultNum = 0.0;  
    private String operator = "=";  
    private boolean operateValidFlag = true;  
  
    /** 
     * ���캯�� 
     */  
    public Calculator() {  
        super();  
        init();  
        this.setBackground(Color.LIGHT_GRAY);  
        this.setJMenuBar(mb1);
        this.setTitle("������");  
        this.setLocation(500,300);
        this.setSize(400,350);
        this.setResizable(false);  
    }  
   
    private void init() {
    	mb1.add(m1);
    	mb1.add(m2);
    	m1.add(new JMenuItem("�ļ�>"));
    	JMenuItem itemExit=new JMenuItem("�˳�");
    	JMenuItem itemAbout=new JMenuItem("����");
    	m1.add(itemExit);
    	m2.add(itemAbout);
    	itemExit.addActionListener(this);
    	itemAbout.addActionListener(this);
    	
        resultText.setHorizontalAlignment(JTextField.RIGHT);   // �ı����е����ݲ����Ҷ��뷽ʽ  
        resultText.setEditable(false);    
        resultText.setBackground(Color.white);
  
        JPanel calckeysPanel = new JPanel();  
        calckeysPanel.setLayout(new GridLayout(5, 4));  
        for (int i = 0; i < KEYS.length; i++) {  
            keys[i] = new JButton(KEYS[i]);  
            calckeysPanel.add(keys[i]);   
        }  
        
        JPanel commandsPanel = new JPanel();  
        commandsPanel.setLayout(new GridLayout(1, 2));  
        for (int i = 0; i < COMMAND.length; i++) {  
            commands[i] = new JButton(COMMAND[i]);  
            commandsPanel.add(commands[i]);  
        } 
        //�½�һ����Ļ���,��calckeysPanel��commandsPanel������ڼ��������в����ı�����ڱ���    
        JPanel panel1 = new JPanel();  
        panel1.setLayout(new BorderLayout()); 
        panel1.add("North", commandsPanel);  
        panel1.add("Center", calckeysPanel); 

        // ���岼��  
        getContentPane().setLayout(new BorderLayout());  
        getContentPane().add("Center", panel1); 
        getContentPane().add("North",resultText);
        
        // Ϊ����ť����¼�������  
        for (int i = 0; i < KEYS.length; i++) {  
            keys[i].addActionListener(this);  
        }  
        for (int i = 0; i < COMMAND.length; i++) {  
            commands[i].addActionListener(this);  
        }  
       
    }  
  
    /** 
     * �����¼� 
     */  
    public void actionPerformed(ActionEvent e) {  
        String label = e.getActionCommand(); 
        if(label.equals("�˳�"))
        	System.exit(0);
        if(label.equals("����")) {	//������Ϣ��ʾ�� 
        	JOptionPane.showMessageDialog(null, "��鿴�����ĵ�","����",JOptionPane.INFORMATION_MESSAGE); 
        }
        if (label.equals(COMMAND[0])) {  
            handleBackspace();  
        } else if (label.equals(COMMAND[1])) {  
            handleC();  
        } else if ("0123456789.".indexOf(label) >= 0) {  
            handleNumber(label);  
        } else {   
            handleOperator(label);  
        }  
    }  
  
    /** 
     * ����Backspace�������µ��¼� 
     */  
    private void handleBackspace() {  
        String text = resultText.getText();  
        int i = text.length();  
        if (i > 0) {  
            text = text.substring(0, i - 1);  
            if (text.length() == 0) {  
                resultText.setText("0");  
                firstDigit = true;  
                operator = "=";  
            } else {  
                resultText.setText(text);  
            }  
        }  
    }  
  
    /** 
     * �������ּ������µ��¼� 
     */  
    private void handleNumber(String key) {  
        if (firstDigit) {  
            resultText.setText(key);  
        } else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {  
            // �������С���㣬����֮ǰû��С���㣬��С���㸽�ڽ���ı���ĺ���  
            resultText.setText(resultText.getText() + ".");  
        } else if (!key.equals(".")) {  
            resultText.setText(resultText.getText() + key);  
        }   
        firstDigit = false;  
    }  
  
    /** 
     * ����C�������µ��¼� 
     */  
    private void handleC() {  
        resultText.setText("0");  
        firstDigit = true;  
        operator = "=";  
    }  
  
    /** 
     * ����������������µ��¼� 
     */  
    private void handleOperator(String key) {  
        if (operator.equals("/")) {  
            // ��������  
            if (getNumberFromText() == 0.0) {  
                operateValidFlag = false;  
                resultText.setText("��������Ϊ��");  
            } else {  
                resultNum /= getNumberFromText();  
            }  
        } else if (operator.equals("1/x")) {  
            // ��������  
            if (resultNum == 0.0) {   
                operateValidFlag = false;  
                resultText.setText("��û�е���");  
            } else {  
                resultNum = 1 / resultNum;  
            }  
        } else if (operator.equals("+")) {  
            // �ӷ�����  
            resultNum += getNumberFromText();  
        } else if (operator.equals("-")) {  
            // ��������  
            resultNum -= getNumberFromText();  
        } else if (operator.equals("*")) {  
            // �˷�����  
            resultNum *= getNumberFromText();  
        } else if (operator.equals("sqrt")) {  
            // ƽ��������  
            resultNum = Math.sqrt(resultNum);  
        } else if (operator.equals("%")) {  
            // �ٷֺ����㣬����100 
        	resultNum /=100;    	
        } else if (operator.equals("+/-")) {  
            // ������������  
            resultNum = resultNum * (-1);  
        } else if (operator.equals("=")) {  
            // ��ֵ����  
            resultNum = getNumberFromText();  
        }  
        if (operateValidFlag) {  
            // ˫���ȸ�����������  
            long t1;  
            double t2;  
            t1 = (long) resultNum;  
            t2 = resultNum - t1;  
            if (t2 == 0) {  
                resultText.setText(String.valueOf(t1));  
            } else {  
                resultText.setText(String.valueOf(resultNum));  
            }  
        }  
        // ����������û����İ�ť  
        operator = key;  
        firstDigit = true;//�Ƚ�����һ�β���,�ٽ����β���(���һ�β���)��������,����ÿ�β���ǰ���Ƚ�����һ�β���,Ĭ�ϲ���Ϊ"=" 
        operateValidFlag = true;  
    }  
  
    /** 
     * �ӽ���ı����л�ȡ���� 
     */  
    private double getNumberFromText() {  
        double result = 0;  
        try {  
            result = Double.valueOf(resultText.getText()).doubleValue(); 
        } catch (NumberFormatException e) {  
        }  
        return result;  
    }  
  
    public static void main(String args[]) {  
    	Calculator calculator1 = new Calculator();  
        calculator1.setVisible(true);  
        calculator1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
}