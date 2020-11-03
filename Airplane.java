import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class Airplane {
    static class Section {
        int row;
        int col;
        int[][] arrangement;
        Section(int row, int col) {
            this.row = row;
            this.col = col;
            this.arrangement = new int[row][col];
        }
    }
    private static int maxRows = 0, maxCols = 0, totalPass, currentPass = 1;
    private static List<Section> sections;
    private static void fillAisle() {
        int row = 0;
        while (row < maxRows && currentPass <= totalPass) {
            for(int i = 0; i < sections.size(); i++) {
                Section section = sections.get(i);
                if(section.row > row) {
                    if (i == 0) {
                        section.arrangement[row][section.col - 1] = currentPass;
                    }
//                     else if (i == maxCols - 1) {
//                         section.arrangement[row][0] =  currentPass;
//                     }
                    else {
                        section.arrangement[row][0] =  currentPass;
                        if(section.col - 1 != 0) {
                            currentPass++;
                            if(currentPass > totalPass)
                                return;
                            if(i != (sections.size()-1)) {
                                section.arrangement[row][section.col - 1] = currentPass;
                            }
                            else{
                                currentPass--;
                            }

                        }
                    }
                    currentPass++;
                    if(currentPass > totalPass)
                        return;
                }
            }
            row++;
        }
    }
    private static void fillWindow() {
        int row = 0;
        while (row < maxRows && currentPass <= totalPass) {
            Section section = sections.get(0);
            if(section.row > row ) {
                section.arrangement[row][0] =  currentPass;
                currentPass++;
            }
            if(currentPass > totalPass)
                return;
            Section lastSection = sections.get(sections.size() - 1);
            if(lastSection.row > row) {
                lastSection.arrangement[row][lastSection.col - 1] =  currentPass;
                currentPass++;
            }
            if(currentPass > totalPass)
                return;
            row++;
        }
    }
    private static void fillMid() {
        int row = 0;
        while (row < maxRows && currentPass <= totalPass) {
            for(int i = 0; i < sections.size(); i++) {
                Section section = sections.get(i);
                if(section.row > row && section.col > 2) {
                    for(int j = 1; j < section.col - 1; j++) {
                        section.arrangement[row][j] =  currentPass;
                        currentPass++;
                        if(currentPass > totalPass)
                            return;
                    }
                }
            }
            row++;
        }
    }
    private static void print(Section section) {
        for(int i = 0; i < section.row; i++) {
            for (int j = 0; j < section.col; j++) {
                System.out.print(section.arrangement[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        sections = new LinkedList<>();
        int[][] twoDimentional = {{5,2},{2,2},{3,3},{4,4},{6,8}};
        int a = 0;
        int b = 0;
        for(int i = 0 ; i < twoDimentional.length ; i++){
            for(int j = 0 ; j < 1; j++){
                a = twoDimentional[i][j]; }
            for(int j = 1 ; j < 2; j++) {
                b = twoDimentional[i][j];
            }
            sections.add(new Section(a, b));
        }
        totalPass = 20;
        for (Section section : sections) {
            maxRows += section.row;
            if (maxCols < section.col) maxCols = section.col;
            else maxCols = maxCols;
        }
        fillAisle();
        fillWindow();
        fillMid();
        System.out.println();
        for (int i = 0; i < sections.size(); i++) {
            System.out.println("Section : "+ (i+1));
            print(sections.get(i));
            System.out.println();
        }
    }
}
