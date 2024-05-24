package model;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.Part;

import util.StringUtils;

public class ProductModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int productId;
    private String productName;
    private String productDesc;
    private int categoryId;
    private BigDecimal productPrice;
    private int stockQty;
    private String stockLevel;
    private String productImgUrlFromPart;
    
    public ProductModel(int productId, String productName, String productDesc, int categoryId, BigDecimal productPrice, int stockQty,
			String stockLevel, Part imagePart) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.categoryId = categoryId;
		this.productPrice = productPrice;
		this.stockQty = stockQty;
		this.stockLevel = stockLevel;
		this.productImgUrlFromPart = getImageUrl(imagePart);
	}
    
    public ProductModel() {
    	
    }
	/**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }
    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }
    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * @return the productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }
    /**
     * @param productDesc the productDesc to set
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }
    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    /**
     * @return the productPrice
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }
    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    /**
     * @return the stockQty
     */
    public int getStockQty() {
        return stockQty;
    }
    /**
     * @param stockQty the stockQty to set
     */
    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }
    /**
     * @return the stockLevel
     */
    public String getStockLevel() {
        return stockLevel;
    }
    /**
     * @param stockLevel the stockLevel to set
     */
    public void setStockLevel(String stockLevel) {
        this.stockLevel = stockLevel;
    }

	public String getProductImgUrlFromPart() {
		return productImgUrlFromPart;
	}

	public void setProductImgUrlFromPart(Part part) {
		this.productImgUrlFromPart = getImageUrl(part);
	}
	
	public void setImageUrlFromDB(String imageUrl) {
		this.productImgUrlFromPart = imageUrl;
	}
	
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_PRODUCT;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}
}
