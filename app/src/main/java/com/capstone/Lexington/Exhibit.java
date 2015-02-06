package com.capstone.Lexington;

public class Exhibit
{
  int floor;
  double loc_x;
  double loc_y;
  String name;
  int qr_code;
  String thumb;

  // base constructor
  public Exhibit()
  {
	  // do nothing
  }

  // constructor
  public Exhibit(String paramString, double paramDouble1, double paramDouble2, int paramInt)
  {
    this.name = paramString;
    this.loc_x = paramDouble1;
    this.loc_y = paramDouble2;
    this.floor = paramInt;
  }
  
  public Exhibit(Exhibit old)
  {
	    this.name = old.name;
	    this.loc_x = old.loc_x;
	    this.loc_y = old.loc_y;
	    this.floor = old.floor;
	    this.qr_code = old.qr_code;
	    this.thumb = old.thumb;
  }

  public int getFloor()
  {
    return this.floor;
  }

  public String getName()
  {
    return this.name;
  }

  public int getQR()
  {
    return this.qr_code;
  }

  public String getThumb()
  {
    return this.thumb;
  }

  public double getX()
  {
    return this.loc_x;
  }

  public double getY()
  {
    return this.loc_y;
  }

  public void setFloor(int paramInt)
  {
    this.floor = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setQR(int paramInt)
  {
    this.qr_code = paramInt;
  }

  public void setThumb(String paramString)
  {
    this.thumb = paramString;
  }

  public void setX(double paramDouble)
  {
    this.loc_x = paramDouble;
  }

  public void setY(double paramDouble)
  {
    this.loc_y = paramDouble;
  }
}
