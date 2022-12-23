package org.ecommerceplatform.dataobject;

public class ItemDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.id
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.title
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.price
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    private Double price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.description
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.sales
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    private Integer sales;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column item_info.img_url
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    private String imgUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.id
     *
     * @return the value of item_info.id
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.id
     *
     * @param id the value for item_info.id
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.title
     *
     * @return the value of item_info.title
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.title
     *
     * @param title the value for item_info.title
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.price
     *
     * @return the value of item_info.price
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.price
     *
     * @param price the value for item_info.price
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.description
     *
     * @return the value of item_info.description
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.description
     *
     * @param description the value for item_info.description
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.sales
     *
     * @return the value of item_info.sales
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.sales
     *
     * @param sales the value for item_info.sales
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column item_info.img_url
     *
     * @return the value of item_info.img_url
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column item_info.img_url
     *
     * @param imgUrl the value for item_info.img_url
     *
     * @mbg.generated Fri Sep 16 11:08:36 CST 2022
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}