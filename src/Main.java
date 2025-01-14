import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String id;
    private String password;
    private double balance;
    private int age;

    public Account(String id, String password, double initialBalance, int age) {
        this.id = id;
        this.password = password;
        this.balance = initialBalance;
        this.age = age;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("입금 완료. 현재 잔액: " + balance);
        } else {
            System.out.println("잘못된 금액입니다. 다시 확인해주세요.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("출금 완료. 현재 잔액: " + balance);
        } else {
            System.out.println("출금 실패: 잔액이 부족하거나 잘못된 금액입니다.");
        }
    }

    public void displayAccountInfo() {
        System.out.println("아이디: " + id);
        System.out.println("비밀번호: " + password);
        System.out.println("나이: " + age);
        System.out.println("잔액: " + balance);
    }

    public static Account searchInfo(ArrayList<Account> accounts, Scanner sc){
        System.out.print("조회할 아이디 입력: ");
        String searchId = sc.next();
        System.out.print("비밀번호 입력: ");
        String searchPassword = sc.next();
        for (Account account : accounts) {
            if (account.authenticate(searchId, searchPassword)) {
                return account;
            }
        }
        return null;
    }

    public boolean authenticate(String inputId, String inputPassword){
        return this.id.equals(inputId) && this.password.equals(inputPassword);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>(); // list 자료 구조 생성.

        while (true) {
            System.out.println("********** BANK ************");
            System.out.println("* 1 추가");
            System.out.println("* 2 조회");
            System.out.println("* 3 입금");
            System.out.println("* 4 출금");
            System.out.println("* 5 삭제");
            System.out.println("* 9 종료");
            System.out.print("항목을 선택하세요: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("아이디 입력: ");
                    String id = sc.next();
                    System.out.print("비밀번호 입력: ");
                    String password = sc.next();
                    System.out.print("나이 입력: ");
                    int age = sc.nextInt();
                    System.out.print("초기 잔액 입력: ");
                    double balance = sc.nextDouble();

                    accounts.add(new Account(id, password, balance, age));
                    System.out.println("계좌가 추가되었습니다.");
                    break;

                case 2:
                    Account searchAccount = Account.searchInfo(accounts,sc);
                    if (searchAccount != null) {
                        searchAccount.displayAccountInfo();
                    } else {
                        System.out.println("계좌를 찾을수 없습니다.");
                    }
                    break;

                case 3:
                   Account depositAccount = Account.searchInfo(accounts,sc);
                    if (depositAccount != null) {
                        System.out.print("입금 금액 입력: ");
                        double depositAmount = sc.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("계좌를 찾을 수 없습니다.");
                    }
                    break;

                case 4:
                    Account withdrawAccount = Account.searchInfo(accounts,sc);
                    if (withdrawAccount != null) {
                        System.out.print("출금 금액 입력: ");
                        double withdrawAmount = sc.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("계좌를 찾을 수 없습니다.");
                    }
                    break;

                case 5:
                    Account deleteAccount = Account.searchInfo(accounts,sc);
                    if (deleteAccount != null) {
                        System.out.println("게좌를 삭제하시겠습니까?");
                        String ch = sc.next();
                        if (ch.equals("y")) {
                            accounts.remove(deleteAccount);
                            System.out.println("계좌가 삭제되었습니다.");
                        } else if(ch.equals("n")){
                            System.out.println("취소되었습니다.");
                        } else {
                            System.out.println("계좌를 찾을 수 없습니다.");
                        }
                    }
                    break;



                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
                    break;
            }
        }
    }
}
