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
    private JMenu m1=new JMenu("文件");
    private JMenu m2=new JMenu("帮助");
    
    private boolean firstDigit = true;   
    private double resultNum = 0.0;  
    private String operator = "=";  
    private boolean operateValidFlag = true;  
  
    /** 
     * 构造函数 
     */  
    public Calculator() {  
        super();  
        init();  
        this.setBackground(Color.LIGHT_GRAY);  
        this.setJMenuBar(mb1);
        this.setTitle("计算器");  
        this.setLocation(500,300);
        this.setSize(400,350);
        this.setResizable(false);  
    }  
   
    private void init() {
    	mb1.add(m1);
    	mb1.add(m2);
    	m1.add(new JMenuItem("文件>"));
    	JMenuItem itemExit=new JMenuItem("退出");
    	JMenuItem itemAbout=new JMenuItem("关于");
    	m1.add(itemExit);
    	m2.add(itemAbout);
    	itemExit.addActionListener(this);
    	itemAbout.addActionListener(this);
    	
        resultText.setHorizontalAlignment(JTextField.RIGHT);   // 文本框中的内容采用右对齐方式  
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
        //新建一个大的画板,将calckeysPanel和commandsPanel画板放在计算器的中部，文本框放在北部    
        JPanel panel1 = new JPanel();  
        panel1.setLayout(new BorderLayout()); 
        panel1.add("North", commandsPanel);  
        panel1.add("Center", calckeysPanel); 

        // 整体布局  
        getContentPane().setLayout(new BorderLayout());  
        getContentPane().add("Center", panel1); 
        getContentPane().add("North",resultText);
        
        // 为各按钮添加事件侦听器  
        for (int i = 0; i < KEYS.length; i++) {  
            keys[i].addActionListener(this);  
        }  
        for (int i = 0; i < COMMAND.length; i++) {  
            commands[i].addActionListener(this);  
        }  
       
    }  
  
    /** 
     * 处理事件 
     */  
    public void actionPerformed(ActionEvent e) {  
        String label = e.getActionCommand(); 
        if(label.equals("退出"))
        	System.exit(0);
        if(label.equals("关于")) {	//弹出消息提示框 
        	JOptionPane.showMessageDialog(null, "请查看帮助文档","关于",JOptionPane.INFORMATION_MESSAGE); 
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
     * 处理Backspace键被按下的事件 
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
     * 处理数字键被按下的事件 
     */  
    private void handleNumber(String key) {  
        if (firstDigit) {  
            resultText.setText(key);  
        } else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {  
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面  
            resultText.setText(resultText.getText() + ".");  
        } else if (!key.equals(".")) {  
            resultText.setText(resultText.getText() + key);  
        }   
        firstDigit = false;  
    }  
  
    /** 
     * 处理C键被按下的事件 
     */  
    private void handleC() {  
        resultText.setText("0");  
        firstDigit = true;  
        operator = "=";  
    }  
  
    /** 
     * 处理运算符键被按下的事件 
     */  
    private void handleOperator(String key) {  
        if (operator.equals("/")) {  
            // 除法运算  
            if (getNumberFromText() == 0.0) {  
                operateValidFlag = false;  
                resultText.setText("除数不能为零");  
            } else {  
                resultNum /= getNumberFromText();  
            }  
        } else if (operator.equals("1/x")) {  
            // 倒数运算  
            if (resultNum == 0.0) {   
                operateValidFlag = false;  
                resultText.setText("零没有倒数");  
            } else {  
                resultNum = 1 / resultNum;  
            }  
        } else if (operator.equals("+")) {  
            // 加法运算  
            resultNum += getNumberFromText();  
        } else if (operator.equals("-")) {  
            // 减法运算  
            resultNum -= getNumberFromText();  
        } else if (operator.equals("*")) {  
            // 乘法运算  
            resultNum *= getNumberFromText();  
        } else if (operator.equals("sqrt")) {  
            // 平方根运算  
            resultNum = Math.sqrt(resultNum);  
        } else if (operator.equals("%")) {  
            // 百分号运算，除以100 
        	resultNum /=100;    	
        } else if (operator.equals("+/-")) {  
            // 正数负数运算  
            resultNum = resultNum * (-1);  
        } else if (operator.equals("=")) {  
            // 赋值运算  
            resultNum = getNumberFromText();  
        }  
        if (operateValidFlag) {  
            // 双精度浮点数的运算  
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
        // 运算符等于用户按的按钮  
        operator = key;  
        firstDigit = true;//先进行上一次操作,再将本次操作(最后一次操作)赋给操作,所以每次操作前都先进行上一次操作,默认操作为"=" 
        operateValidFlag = true;  
    }  
  
    /** 
     * 从结果文本框中获取数字 
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