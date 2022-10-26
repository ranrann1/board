package board;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JdbcUtil;
import oracle.jdbc.proxy.annotation.Pre;

public class BoardDAO {
	private JdbcUtil ju;
	
	public BoardDAO() {
		ju=JdbcUtil.getInstance();
	}
	
	//삽입(c)
	public int insert(BoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
	
		int ret=-1;
		try {
			con=ju.getConnection();
			String query = "insert into board values (board_seq.nextval, ?, ?, ?, sysdate,0)";
			
			
			pstmt=con.prepareStatement(query);
			//pstmt.setInt(1, vo.getCnt());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			//pstmt.setDate(5, vo.getRegdate());
			//pstmt.setInt(6, vo.getNum());
			//pstmt.executeUpdate();
			
			ret = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	//조회(r)
	public List<BoardVo> selectAll(){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String query = "select num, title, writer,content,regdate,cnt from board";
		ArrayList<BoardVo> ls = new ArrayList<BoardVo>();
		try {
			con=ju.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				BoardVo vo = new BoardVo(
						
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6));
						ls.add(vo);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(stmt!=null) {
				try {
					stmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
	
	
	
	
	//조회2
		public BoardVo selectOne(int num){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
		
			BoardVo vo=null;
			try {
				
				con=ju.getConnection();
				String query = "select * from board where num=?";
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
			if(rs.next()) {
				updateCnt(num);//조회수 증가 
					vo = new BoardVo();
					vo.setNum(rs.getInt("num"));
					vo.setTitle(rs.getString("title"));
					vo.setWriter(rs.getString("writer"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getDate("regdate"));
					vo.setCnt(rs.getInt("cnt"));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) {
					try {
						rs.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if(pstmt!=null) {
					try {
						pstmt.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return vo;
		}
		
		
	//수정(u)
		public int update(BoardVo vo) {
			Connection con=null;
			PreparedStatement pstmt=null;
		
			int ret=-1;
			try {
				con=ju.getConnection();
				String query = "update board set title=?,writer=?,content=? where num=?";
				
				
				pstmt=con.prepareStatement(query);
				//pstmt.setInt(1, vo.getCnt());
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getWriter());
				pstmt.setString(3, vo.getContent());
				//pstmt.setDate(5, vo.getRegdate());
				pstmt.setInt(4, vo.getNum());
				//pstmt.executeUpdate();
				
				ret = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(pstmt!=null) {
					try {
						pstmt.close();
					}catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if(con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return ret;
		}
			
		


//조회수 
public int updateCnt(int num) {
	Connection con=null;
	PreparedStatement pstmt=null;

	int ret=-1;
	try {
		String query = "update board set cnt=cnt+1 where num = ?";

		con=ju.getConnection();
		pstmt=con.prepareStatement(query);
		pstmt.setInt(1, num);

		
		ret = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	return ret;
}
	

	//삭제(d)
	public int delete(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int ret=-1;
		try {
			con=ju.getConnection();
			
			String sql="delete from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			ret=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
}
		
	