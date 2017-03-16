package com.shubh.android.ghoomoghumao;

/**
 * Created by Smily on 2/24/2017.
 */

public class Tour {
    private int mImageId;
    private String mImageText;
    private String mLocation;

    /*
    Following is the constructor to set IconImage.
     */
    public Tour(int imageId, String imageText, String location){
        mImageId = imageId;
        mImageText = imageText;
        mLocation = location;
    }

    /*
    Following is the function returning the image icon.
     */

    public int getImage(){
        return mImageId;
    }

    /*
    Following is the function returning the image text.
     */
    public String getTextOne(){
        return mImageText;
    }

    /*
   Following is the function returning the number text.
    */
    public String getLocation(){
        return mLocation;
    }
}
