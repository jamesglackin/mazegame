/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazegame1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import javax.swing.JTextField;

/**
 *
 * @author james.glackin
 */
public class MazeGame extends javax.swing.JFrame implements KeyListener {


    public MazeGame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homeScreen = new javax.swing.JButton();
        winLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homeScreen.setText("Home Screen");
        homeScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeScreenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(1210, Short.MAX_VALUE)
                .addComponent(winLabel)
                .addGap(118, 118, 118))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeScreen)
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(winLabel)
                .addGap(276, 276, 276)
                .addComponent(homeScreen)
                .addContainerGap(627, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
   
    
    private void homeScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeScreenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_homeScreenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton homeScreen;
    private javax.swing.JLabel winLabel;
    // End of variables declaration//GEN-END:variables

    private boolean greenChecked = false;
    private int moveCount;
    private LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> coordinatesToFill = new LinkedHashMap<>();
    private LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> emptyCoordinates = new LinkedHashMap<>();
    private LinkedHashMap<Character, LinkedHashMap<Integer, Integer>> otherCoordinates = new LinkedHashMap<>();
    private int[] currentPosition;
    private LinkedHashMap<String, Integer> boundaries = new LinkedHashMap();
    private String difficulty;   
    private boolean ignoreWall = false;
    private boolean greenCollected = false;
    private boolean wallRemoved = false;
    private int heightAndWidth = 50;

    @SuppressWarnings("unchecked")
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MazeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MazeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MazeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MazeGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MazeScreen().setVisible(true);
//                
//            }
//        });

        MazeGame mazeGame = new MazeGame();;
//        mazeScreen.setVisible(true);
        Container container = mazeGame.getContentPane(); 
        
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        int[] pos = mazeGame.getCurrentPosition();
                        int currentX = pos[0];
                        int currentY = pos[1];

                        int nextX = currentX;
                        int nextY = currentY - mazeGame.heightAndWidth;

                        dictateMoves("UP", nextX, nextY, currentX, currentY);

                   } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        int[] pos = mazeGame.getCurrentPosition();
                        int currentX = pos[0];
                        int currentY = pos[1];

                        int nextX = currentX;
                        int nextY = currentY + mazeGame.heightAndWidth;

                        dictateMoves("DOWN", nextX, nextY, currentX, currentY);
                   } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        int[] pos = mazeGame.getCurrentPosition();
                        int currentX = pos[0];
                        int currentY = pos[1];

                        int nextX = currentX + mazeGame.heightAndWidth;
                        int nextY = currentY;

                        dictateMoves("RIGHT", nextX, nextY, currentX, currentY);
                   } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        int[] pos = mazeGame.getCurrentPosition();
                        int currentX = pos[0];
                        int currentY = pos[1];

                        int nextX = currentX - mazeGame.heightAndWidth;
                        int nextY = currentY;

                        dictateMoves("LEFT", nextX, nextY, currentX, currentY);
                   }
                e.consume();

                } catch (Exception ex) {
                } 
            }  
            
            public void dictateMoves(String direction, int nextX, int nextY, int currentX, int currentY) throws InterruptedException {
                boolean legal = true;
                boolean skipWalls;
                                
                if (mazeGame.isGreenChecked() == false) {
                    skipWalls = checkIfGreen(nextX, nextY, currentX, currentY);
                    legal = checkMoveIsLegal(nextX, nextY, currentX, currentY, skipWalls);
                } else {
                    if (mazeGame.isWallRemoved() == false) {
                        legal = checkMoveIsLegal(nextX, nextY, currentX, currentY, true);
                        checkIfWallPieceWillBeRemoved(nextX, nextY);
                    } else {
                        legal = checkMoveIsLegal(nextX, nextY, currentX, currentY, false);
                    }
                }
                
                if (legal) {
                    mazeGame.getContentPane();
        //            t2.setSize(new Dimension(500,500));
                    mazeGame.setVisible(true);
                    Graphics g = mazeGame.getGraphics();
                    paint(g, nextX, nextY, currentX, currentY);
                }
                mazeGame.setMoveCount(mazeGame.getMoveCount() +1);     
                mazeGame.winLabel.setText(Integer.toString(mazeGame.moveCount));
                mazeGame.winLabel.setVisible(true);
                mazeGame.winLabel.setPreferredSize(new Dimension(150, 150));
                mazeGame.winLabel.setFont(new Font("Serif", Font.PLAIN, 45));
                checkIfWon(nextX, nextY);
            }
            
            public boolean checkIfGreen(int nextX, int nextY, int currentX, int currentY) {
                LinkedHashMap<Character, LinkedHashMap<Integer, Integer>> startAndEndCoordinates = mazeGame.getOtherCoordinates();
                for (Character coordinate : startAndEndCoordinates.keySet()) {
                    char sORe = coordinate;
                    LinkedHashMap<Integer,Integer> tempXandYCoordinates = startAndEndCoordinates.get(coordinate);
                    for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                        if (sORe == 'C') {
                            if (nextX == tempCoordinates && nextY == tempXandYCoordinates.get(tempCoordinates)) {
                                mazeGame.setIgnoreWall(true);
                                mazeGame.setGreenCollected(true);
                                mazeGame.setGreenChecked(true);
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            
            public void checkIfWallPieceWillBeRemoved(int nextX, int nextY) {
                LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> usedCoordinates = mazeGame.getCoordinatesToFill();
                for (Integer coordinate : usedCoordinates.keySet()) {
                    LinkedHashMap<Integer,Integer> tempXandYCoordinates = usedCoordinates.get(coordinate);
                    for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                        if (tempCoordinates == nextX && tempXandYCoordinates.get(tempCoordinates) == nextY) {
                            mazeGame.setWallRemoved(true);
                            mazeGame.setGreenCollected(false);
                        }                                 
                    }
                }
            }
            
            
            public void checkIfWon(int nextX, int nextY) throws InterruptedException {
                LinkedHashMap<Character, LinkedHashMap<Integer, Integer>> startAndEndCoordinates = mazeGame.getOtherCoordinates();
                for (Character coordinate : startAndEndCoordinates.keySet()) {
                    char sORe = coordinate;
                    LinkedHashMap<Integer,Integer> tempXandYCoordinates = startAndEndCoordinates.get(coordinate);
                    for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                        if (sORe == 'E') {
                            if (nextX == tempCoordinates && nextY == tempXandYCoordinates.get(tempCoordinates)) {
                                System.out.println("WINNER");
                                mazeGame.winLabel.setText("YOU WIN!");
                                mazeGame.winLabel.setVisible(true);
                                mazeGame.winLabel.setPreferredSize(new Dimension(150, 150));
                                mazeGame.winLabel.setFont(new Font("Serif", Font.PLAIN, 45));
                                mazeGame.homeScreen.setVisible(true);
                            }
                        }
                    }
                }
            }
             
            public boolean checkMoveIsLegal(int nextX, int nextY, int currentX, int currentY, boolean skipWalls) {
                boolean legal = true;
                LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> usedCoordinates = mazeGame.getCoordinatesToFill();
                
                if (!skipWalls) {
                    for (Integer coordinate : usedCoordinates.keySet()) {
                        LinkedHashMap<Integer,Integer> tempXandYCoordinates = usedCoordinates.get(coordinate);
                        for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                            if (tempCoordinates == nextX && tempXandYCoordinates.get(tempCoordinates) == nextY) {
                                legal = false;
                            }                                 
                        }
                    }
                }
                LinkedHashMap<String,Integer> boundaries = mazeGame.getBoundaries();

                if ((boundaries.get("Left") > nextX) || (boundaries.get("Top") > nextY) ||
                    (boundaries.get("Right") < nextX) || (boundaries.get("Bottom") < nextY)) {
                    legal = false;
                }
                
                System.out.println((boundaries.get("Left").toString()));
                System.out.println("X = " + nextX);
                System.out.println("Y = " + nextY);
                     
                return legal;
            }
            
            public void paint(Graphics g, int nextX, int nextY, int currentX, int currentY) {
                
                int[] currentPos = {nextX,nextY};
                mazeGame.setCurrentPosition(currentPos);
                
                
                g.setColor(Color.WHITE);
                
                g.fillRect(currentX, currentY, mazeGame.heightAndWidth, mazeGame.heightAndWidth);
                g.setColor(Color.BLACK);
                g.drawRect(currentX, currentY, mazeGame.heightAndWidth, mazeGame.heightAndWidth);
//                g.fillRect(currentX, currentY, 50, 50);
                
                g.setColor(Color.RED);
                g.drawRect(nextX, nextY, mazeGame.heightAndWidth, mazeGame.heightAndWidth);
                g.fillRect(nextX, nextY, mazeGame.heightAndWidth, mazeGame.heightAndWidth);
            }
        
        };
        JTextField textField = new JTextField();
        textField.addKeyListener(keyListener);
//        textField.setSize(new Dimension(100,100));
        container.add(textField, BorderLayout.NORTH);
        mazeGame.pack();
        mazeGame.setVisible(true);
    }
    
    public LinkedHashMap<Integer, LinkedHashMap<Integer,Integer>> create(String difficulty) {
        try {
            
//            FileReader fileReader = new FileReader("C:\\DataGenic\\Apprentice\\Project\\Maze\\maze_" + difficulty + ".txt");

            FileReader fileReader = new FileReader("C:\\DataGenic\\Apprentice\\Project\\Maze\\maze_medium.txt");
            
            // C:\DataGenic\Apprentice\Project\Maze\maze_medium.txt
            // maze_easy
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int yAxis = 50;
            int count = 0;
            setMoveCount(0);
            LinkedHashMap<String, Integer> boundaries = new LinkedHashMap();            
            boundaries.put("Left", 150);
            boundaries.put("Top", yAxis);

            while ((line = bufferedReader.readLine()) != null) {
                String[] letters = line.split("-");
                int length = (letters.length * heightAndWidth) + 100;
                int xAxis = 150;
                boundaries.put("Right", length-heightAndWidth);

                do {
                    for (String letter : letters) {
                        LinkedHashMap<Integer,Integer> tempXandYCoordinates = new LinkedHashMap<>();
                        if (letter.equals("B")) {
                            tempXandYCoordinates.put(xAxis,yAxis);
                            emptyCoordinates.put(count,tempXandYCoordinates);
                        } else if (letter.equals("W")) {
                            tempXandYCoordinates.put(xAxis,yAxis);
                            coordinatesToFill.put(count,tempXandYCoordinates);
                        } else if (letter.equals("S")) {
                            tempXandYCoordinates.put(xAxis,yAxis);
                            otherCoordinates.put('S', tempXandYCoordinates);
                        } else if (letter.equals("E")) {
                            tempXandYCoordinates.put(xAxis,yAxis);
                            otherCoordinates.put('E', tempXandYCoordinates);
                        } else if (letter.equals("C")) {
                            tempXandYCoordinates.put(xAxis,yAxis);
                            otherCoordinates.put('C', tempXandYCoordinates);
                        } else {
                        }
                        if (letter.equals(",")) {
                            yAxis += heightAndWidth;
                        }
                        if (xAxis == length) {
                            break;
                        }
                        xAxis += heightAndWidth;
                        count ++;
                    }
                } while (xAxis != length);
                boundaries.put("Bottom", yAxis-heightAndWidth);
            }
            setOtherCoordinates(otherCoordinates);
            setEmptyCoordinates(emptyCoordinates);
            setBoundaries(boundaries);            
            return coordinatesToFill;
        } catch (Exception e) {

        }
        return coordinatesToFill;
    }
    
    //String difficulty, Graphics g
    public void paintComponent(Graphics g) {
        
        LinkedHashMap<Integer, LinkedHashMap<Integer,Integer>> coordinatesToFill = create(difficulty);  
        LinkedHashMap<Integer, LinkedHashMap<Integer,Integer>> emptyCoordinates = getEmptyCoordinates();
        LinkedHashMap<Character, LinkedHashMap<Integer,Integer>> startAndEndCoordinates = getOtherCoordinates();
        
        for (Integer coordinate : coordinatesToFill.keySet()) {
            LinkedHashMap<Integer,Integer> tempXandYCoordinates = coordinatesToFill.get(coordinate);
            for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                g.drawRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
                g.setColor(Color.BLACK);
                g.fillRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
            }
        }

        for (Integer coordinate : emptyCoordinates.keySet()) {
            LinkedHashMap<Integer,Integer> tempXandYCoordinates = emptyCoordinates.get(coordinate);
            for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                g.setColor(Color.BLACK);
                g.drawRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
            }
        }

        for (Character coordinate : startAndEndCoordinates.keySet()) {
            char sORe = coordinate;
            LinkedHashMap<Integer,Integer> tempXandYCoordinates = startAndEndCoordinates.get(coordinate);
            for (Integer tempCoordinates : tempXandYCoordinates.keySet()) {
                g.drawRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
                if (sORe == 'S') {
                    g.setColor(Color.RED);
                    g.fillRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
                    int[] currentPos = {tempCoordinates, tempXandYCoordinates.get(tempCoordinates)};
                    setCurrentPosition(currentPos);
                } else if (sORe == 'E') {
                    g.setColor(Color.BLUE);
                    g.fillRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
                } else if (sORe == 'C') {
                    g.setColor(Color.GREEN);
                    g.fillRect(tempCoordinates, tempXandYCoordinates.get(tempCoordinates),heightAndWidth,heightAndWidth);
                }
            }
        }
//        String[] s = null;
//        main(s);
    }
    
    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }
    
    public int[] getCurrentPosition() {
        return currentPosition;
    }
 
     public LinkedHashMap<Character, LinkedHashMap<Integer, Integer>> getOtherCoordinates() {
         return otherCoordinates;
     }

     public void setOtherCoordinates(LinkedHashMap<Character, LinkedHashMap<Integer, Integer>> otherCoordinates) {
         this.otherCoordinates = otherCoordinates;
     }


     public LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> getCoordinatesToFill() {
         return coordinatesToFill;
     }

     public void setCoordinatesToFill(LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> coordinatesToFill) {
         this.coordinatesToFill = coordinatesToFill;
     }

     public LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> getEmptyCoordinates() {
         return emptyCoordinates;
     }

     public void setEmptyCoordinates(LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> emptyCoordinates) {
         this.emptyCoordinates = emptyCoordinates;
     }
     
     
    public LinkedHashMap<String, Integer> getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(LinkedHashMap<String, Integer> boundaries) {
        this.boundaries = boundaries;
    }
    
    
    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
    
    
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public boolean isIgnoreWall() {
        return ignoreWall;
    }

    public void setIgnoreWall(boolean ignoreWall) {
        this.ignoreWall = ignoreWall;
    }
    
    public boolean isGreenCollected() {
        return greenCollected;
    }

    public void setGreenCollected(boolean greenCollected) {
        this.greenCollected = greenCollected;
    }
    
  
    public boolean isWallRemoved() {
        return wallRemoved;
    }

    public void setWallRemoved(boolean wallRemoved) {
        this.wallRemoved = wallRemoved;
    }

    public boolean isGreenChecked() {
        return greenChecked;
    }

    public void setGreenChecked(boolean greenChecked) {
        this.greenChecked = greenChecked;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
