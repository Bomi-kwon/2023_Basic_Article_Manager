package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		MemberController membercontroller = new MemberController(sc);
		ArticleController articlecontroller = new ArticleController(sc);

		articlecontroller.makeTestData();
		membercontroller.makeTestData();

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			} else if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
			} else {
				String[] cmdBits = cmd.split(" ");
				if (cmdBits.length == 1) {
					System.out.println("존재하지 않는 명령어입니다.");
					continue;
				}
				String controllername = cmdBits[0];
				String methodname = cmdBits[1];
				
				Controller controller = null;
				
				if (controllername.equals("article")) {
					controller = articlecontroller;
				} else if (controllername.equals("member")) {
					controller = membercontroller;
				} else {
					System.out.println("존재하지 않는 명령어입니다.");
					continue;
				}
				
				controller.run(cmd, methodname);
			}
		}

		System.out.println("== 프로그램 끝 ==");
		sc.close();
	}

}
