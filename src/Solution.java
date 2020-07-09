import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution  {

    public static double[][] addMatrices(double[][] first, double[][] second) {
        double[][] sum = new double[first.length][first[0].length];
        for (int i = 0; i < first.length; i++) {
            for (int j = 0; j < first[0].length; j++) {
                sum[i][j] = first[i][j] + second[i][j];
            }
        }
        return sum;
    }
    public static double[][] multiplyMatrixByConstant(double[][]  matrix, double constant) {
        double[][] product = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                product[i][j] = matrix[i][j] * constant;
            }
        }
      return product;
    }
    public static double[][] matricesMultiply(double[][] opOne, double[][] opTwo) {
        int row = opOne.length;
        int column = opTwo[0].length;
        double[][] product = new double[row][column];
        //method to multiply by cell
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < product[0].length; j++) {
                product[i][j] = multiplyByCell(opOne, opTwo, i, j);
            }
        }
        return product;
    }
    public static double multiplyByCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
    //transpose
    public static void transposeMainDiagonal(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }
    public static void transposeSideDiagonal(double[][] matrix) {
        int rows = matrix[0].length - 1;
        int columns = matrix.length - 1;
        for (int i = rows; i >= 0; i--) {
            for (int j = columns; j >= 0; j--) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }
    public  static void transposeVertical(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length-1;
        for (int i = 0; i < rows; i++) {
            for (int j = columns; j >= 0; j--) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void transposeHorizontal(double[][] matrix) {
        int rows = matrix.length-1;
        int columns = matrix[0].length;
        for (int i = rows; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void transposeOptions(double[][] matrix, int option) {
        switch (option) {
            case 1:
                transposeMainDiagonal(matrix);
                break;
            case 2:
                transposeSideDiagonal(matrix);
                break;
            case 3:
                transposeVertical(matrix);
                break;
            case 4:
                transposeHorizontal(matrix);
                break;
        }
    }
    public static void print2DArray(double[][] array) {
      for (int k = 0; k < array.length; k++) {
          for (int l = 0; l < array[0].length; l++) {
              System.out.printf(array[k][l] + " ");
          }
          System.out.println();
      }
    }
    //determinant methods
    public static double manufactureMinor(double[][] matrix) {
        double minor = 0;
        minor = ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
        return minor;
    }
    public static double[][] spawnSubMatrix(double[][] matrix, int row, int column) {
        double[][] subMatrix = new double[matrix.length-1][matrix[0].length-1];
        ArrayList<Double> temp = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i != row && j != column)
                    temp.add(matrix[i][j]);
            }
        }
        //place collected values in sub array
        int index = 0;
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix[0].length; j++) {
                subMatrix[i][j] = temp.get(index);
                index++;
            }
        }
        return subMatrix;
    }
    public static double determineDeterminant(double[][] matrix) {
        double determined = 0;
        if (matrix.length < 3) {
            return manufactureMinor(matrix);
        }else  {
            for (int i = 0; i < matrix[0].length; i++) {
                double[][] subMatrix = spawnSubMatrix(matrix, 0, i);
                if (i%2 == 0) {
                    determined += matrix[0][i] * determineDeterminant(subMatrix);
                }else {
                    determined -= matrix[0][i] * determineDeterminant(subMatrix);
                }
            }
        }
        return determined;
    }

    //inverse methods
    public static double[][] cofactorMatrix(double[][] matrix){
        double[][] cofactorMatrix = new double[matrix.length][matrix[0].length];
        double[][] tempMatrix;
        for (int i = 0; i < cofactorMatrix.length; i++) {
            for (int j = 0; j < cofactorMatrix[0].length; j++) {
                tempMatrix = spawnSubMatrix(matrix, i, j);
                cofactorMatrix[i][j] = Math.pow(-1, i+1+j+1) * determineDeterminant(tempMatrix);
            }
        }
        cofactorMatrix = transposeGen(cofactorMatrix);
        return cofactorMatrix;
    }
    public static double[][] transposeGen(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] temp = new   double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = matrix[j][i];
            }
        }
        return temp;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        int row = 0;
        int column = 0;
               do {
                   //display menu options
                   System.out.println("1. Add matrices\n" +
                           "2. Multiply matrix to a constant\n" +
                           "3. Multiply matrices\n" +
                           "4. Transpose matrix\n" +
                           "5. Calculate a determinant\n" +
                           "6. Inverse matrix\n" +
                           "0. Exit" +
                           "");
                   //take input from user
                   option = scanner.nextInt();
                   switch (option) {
                case 1:
                    //matrix addition
                    row = scanner.nextInt();
                    column = scanner.nextInt();
                    double[][] first = new double[row][column];
                    for (int i = 0; i < first.length; i++) {
                        for (int j = 0; j < first[0].length; j++) {
                            first[i][j] = scanner.nextDouble();
                        }
                    }
                    row = scanner.nextInt();
                    column = scanner.nextInt();
                    double[][] second = new double[row][column];
                    for (int i = 0; i < second.length; i++) {
                        for (int j = 0; j < second[0].length; j++) {
                            second[i][j] = scanner.nextDouble();
                        }
                    }
                    double[][] sum = addMatrices(first, second);
                    print2DArray(sum);
                    break;
                //end case 1
                case 2:
                    //multiply by constant
                    row = scanner.nextInt();
                    column = scanner.nextInt();
                    double[][] matrix = new double[row][column];
                    for (int i = 0; i < matrix.length; i++) {
                        for (int j = 0; j < matrix[0].length; j++) {
                            matrix[i][j] = scanner.nextDouble();
                        }
                    }
                    double constant = scanner.nextDouble();
                    double[][] product = multiplyMatrixByConstant(matrix, constant);
                    print2DArray(product);
                    break;
                //end case 2
                case 3:
                    //matrix multiplication
                    System.out.println("Enter size of first matrix: ");
                    row = scanner.nextInt();
                    column = scanner.nextInt();
                    double[][] opOne = new double[row][column];
                    for (int i = 0; i < opOne.length; i++) {
                        for (int j = 0; j < opOne[0].length; j++) {
                            opOne[i][j] = scanner.nextDouble();
                        }
                    }
                    System.out.println("Enter size of second matrix: ");
                    row = scanner.nextInt();
                    column = scanner.nextInt();
                    double[][] opTwo = new double[row][column];
                    for (int i = 0; i < opTwo.length; i++) {
                        for (int j = 0; j < opTwo[0].length; j++) {
                            opTwo[i][j] = scanner.nextDouble();
                        }
                    }
                    double[][] prod = matricesMultiply(opOne, opTwo);
                    print2DArray(prod);
                    break;
                //end case
                 case 4:
                     //transpose matrix
                     System.out.println("1. Main diagonal\n" +
                             "2. Side diagonal\n" +
                             "3. Vertical line\n" +
                             "4. Horizontal line");
                     option =scanner.nextInt();
                     System.out.println("Enter Matrix Size: ");
                     row = scanner.nextInt();
                     column = scanner.nextInt();
                     double[][] transpose = new double[row][column];
                     for (int i = 0; i < transpose.length; i++) {
                         for (int j = 0; j < transpose[0].length; j++) {
                             transpose[i][j] = scanner.nextDouble();
                         }
                     }
                     System.out.println("Transposed...");
                     transposeOptions(transpose, option);
                     System.out.println();
                     break;
                     //end case
                  case 5:
                      //determinant
                      System.out.println("Enter matrix size: ");
                      row = scanner.nextInt();
                      column = scanner.nextInt();
                      double[][] det = new double[row][column];
                      System.out.println("Enter matrix: ");
                      for (int i = 0; i < det.length; i++) {
                          for (int j = 0; j < det[0].length; j++) {
                              try {
                                  det[i][j] = scanner.nextDouble();
                              }catch (InputMismatchException ime){}
                          }
                      }
                      System.out.println("The result is:\n" + determineDeterminant(det));
                      break;
                      //end case
                  case 6:
                      System.out.println("Enter matrix size: ");
                      row = scanner.nextInt();
                      column = scanner.nextInt();
                      double[][] iMatrix = new double[row][column];
                      System.out.println("Enter matrix: ");
                      for (int i = 0; i < iMatrix.length; i++) {
                          for (int j = 0; j < iMatrix[0].length; j++) {
                              iMatrix[i][j] = scanner.nextDouble();
                          }
                      }
                      double[][] cofactorMatrix = cofactorMatrix(iMatrix);
                      iMatrix = multiplyMatrixByConstant(cofactorMatrix, 1/determineDeterminant(iMatrix));
                      for (int i = 0; i < iMatrix.length; i++) {
                          for (int j = 0; j < iMatrix[0].length; j++) {
                              System.out.printf("%5.2f", iMatrix[i][j]);
                              System.out.print(" ");
                          }
                          System.out.println();
                      }
                      break;
                      //end case
                 case 0:
                   break;
                default:
                    System.out.println("Invalid!");
            }
        } while (option != 0);

       scanner.close();
    }
}
