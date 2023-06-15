package test.com.mini.comment;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
    private int cnum;
    private String content;
    private String writer;
    private Date wdate;
    private String bid;
}
