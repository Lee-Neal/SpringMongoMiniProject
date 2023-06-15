package test.com.mini.board;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	
	private String bid;
	private int num;
	private String title;
	private String content;
	private String writer;
	private Date wdate;
}
