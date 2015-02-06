package com.capstone.Lexington;

public class Media
{
  String desc;
  int id;
  String name;
  String path;
  int qr_code;
  int type;
  
  // base constructor
  public Media()
  {
	  // do nothing
  }
  
  // constructor
  public Media(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    this.name = paramString1;
    this.desc = paramString2;
    this.path = paramString3;
    this.type = paramInt1;
    this.qr_code = paramInt2;
  }

  public String getDesc()
  {
    return this.desc;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPath()
  {
    return this.path;
  }

  public int getQR()
  {
    return this.qr_code;
  }

  public int getType()
  {
    return this.type;
  }

  public void setDesc(String paramString)
  {
    this.desc = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPath(String paramString)
  {
    this.path = paramString;
  }

  public void setQR(int paramInt)
  {
    this.qr_code = paramInt;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}