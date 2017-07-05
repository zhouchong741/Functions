package xxx.functions.modules.login.data;

import android.os.Parcel;

import com.jiae.herbs.baselib.mvp.bean.Data;

import java.util.List;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/7/5 13:38
 */

public class ImUserData extends Data {


    /**
     * custid : 21133
     * nickname :
     * realname :
     * firstname :
     * secondname :
     * pinyinname :
     * custtype : 1
     * idenum : 30634
     * status : 1
     * sex : 1
     * birthday : 0000-00-00
     * age : 0
     * country :
     * zone :
     * area :
     * address :
     * hometown :
     * mobile : 13000000001
     * email :
     * username :
     * signature :
     * description :
     * auth : 040f876f07bba71688df22cf125c999f
     * webauth : d241ec432cf976b78a2cd29c016311e7
     * lastloginip : 114.55.53.55
     * logintimes : 12
     * isonline : 1
     * issuper : 0
     * pid : 0
     * createtime : 2017-06-29 20:52:15
     * updatetime : 2017-06-29 20:52:15
     * msgsetting : {"shock":"0","beep":"0","push":"0"}
     * dynamicsetting :
     * interests :
     * custsource : 1
     * university :
     * school :
     * majorclass :
     * entranceyear : 0000-00-00
     * sdtno :
     * qq : 0
     * contact :
     * contactmobile :
     * iskitchener : 0
     * backgroundimg : []
     * ordnum : 0
     * dishes :
     * videourl :
     * identitycard :
     * kitchencard :
     * healthcard :
     * token : dBLMpeLw5c7yBek98xrLfrm3tub-fHhB-F9NI5jeWCvI9eIX_jydHg4TJNA1GGJV
     * ucrtoken : eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBfaWQiOiJxaXl1bnhpbiIsImV4cCI6MTQ5OTQ5MjA3NCwiaWF0IjoxNDk5MjMyODc0LCJyX2lkIjoiMjExMzMiLCJzdWIiOiI5N2ViMmY2MDI2ZTU0NmE0ODhkMThjZDUxZWU4OWY4MiJ9.g95odW2c8ZkqLfbK_TyM_rvuMJshWA_MF0JJKoU6wUJK2SigIIAg9SV-hIghJk56l6SR915vnDG2CRKFsF0_j7cp6l5Obx4XHYl_iNtHBI61qS7DlnDdkoNzgTk_mVmxBwa9OnJ5L0GdVtrUTyJSKSkAx664movMT86lNAzOkqg
     * ucropenid : 97eb2f6026e546a488d18cd51ee89f82
     */

    private String custid;
    private String nickname;
    private String realname;
    private String firstname;
    private String secondname;
    private String pinyinname;
    private String custtype;
    private String idenum;
    private String status;
    private String sex;
    private String birthday;
    private String age;
    private String country;
    private String zone;
    private String area;
    private String address;
    private String hometown;
    private String mobile;
    private String email;
    private String username;
    private String signature;
    private String description;
    private String auth;
    private String webauth;
    private String lastloginip;
    private String logintimes;
    private String isonline;
    private String issuper;
    private String pid;
    private String createtime;
    private String updatetime;
    private MsgsettingBean msgsetting;
    private String dynamicsetting;
    private String interests;
    private String custsource;
    private String university;
    private String school;
    private String majorclass;
    private String entranceyear;
    private String sdtno;
    private String qq;
    private String contact;
    private String contactmobile;
    private String iskitchener;
    private String ordnum;
    private String dishes;
    private String videourl;
    private String identitycard;
    private String kitchencard;
    private String healthcard;
    private String token;
    private String ucrtoken;
    private String ucropenid;
    private List<?> backgroundimg;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nickname);
        dest.writeString(this.realname);
        dest.writeString(this.firstname);
        dest.writeString(this.secondname);
        dest.writeString(this.pinyinname);
        dest.writeString(this.custtype);
        dest.writeString(this.idenum);
        dest.writeString(this.status);
        dest.writeString(this.sex);
        dest.writeString(this.birthday);
        dest.writeString(this.age);
        dest.writeString(this.country);
        dest.writeString(this.zone);
        dest.writeString(this.area);
        dest.writeString(this.address);
        dest.writeString(this.hometown);
        dest.writeString(this.mobile);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.signature);
        dest.writeString(this.description);
        dest.writeString(this.auth);
        dest.writeString(this.webauth);
        dest.writeString(this.lastloginip);
        dest.writeString(this.logintimes);
        dest.writeString(this.isonline);
        dest.writeString(this.issuper);
        dest.writeString(this.pid);
        dest.writeString(this.createtime);
        dest.writeString(this.updatetime);
        dest.writeString(this.dynamicsetting);
        dest.writeString(this.interests);
        dest.writeString(this.custsource);
        dest.writeString(this.university);
        dest.writeString(this.school);
        dest.writeString(this.majorclass);
        dest.writeString(this.entranceyear);
        dest.writeString(this.sdtno);
        dest.writeString(this.qq);
        dest.writeString(this.contact);
        dest.writeString(this.contactmobile);
        dest.writeString(this.iskitchener);
        dest.writeString(this.ordnum);
        dest.writeString(this.dishes);
        dest.writeString(this.videourl);
        dest.writeString(this.identitycard);
        dest.writeString(this.kitchencard);
        dest.writeString(this.healthcard);
        dest.writeString(this.token);
        dest.writeString(this.ucrtoken);
        dest.writeString(this.ucropenid);
        dest.writeParcelable(this.msgsetting, flags);
    }

    public ImUserData() {
    }

    public ImUserData(Parcel source) {
        this.custid = source.readString();
        this.custid = source.readString();
        this.nickname = source.readString();
        this.realname = source.readString();
        this.firstname = source.readString();
        this.secondname = source.readString();
        this.pinyinname = source.readString();
        this.custtype = source.readString();
        this.idenum = source.readString();
        this.status = source.readString();
        this.sex = source.readString();
        this.birthday = source.readString();
        this.age = source.readString();
        this.country = source.readString();
        this.zone = source.readString();
        this.area = source.readString();
        this.address = source.readString();
        this.hometown = source.readString();
        this.mobile = source.readString();
        this.email = source.readString();
        this.username = source.readString();
        this.signature = source.readString();
        this.description = source.readString();
        this.auth = source.readString();
        this.webauth = source.readString();
        this.lastloginip = source.readString();
        this.logintimes = source.readString();
        this.isonline = source.readString();
        this.issuper = source.readString();
        this.pid = source.readString();
        this.createtime = source.readString();
        this.updatetime = source.readString();
        this.dynamicsetting = source.readString();
        this.interests = source.readString();
        this.custsource = source.readString();
        this.university = source.readString();
        this.school = source.readString();
        this.majorclass = source.readString();
        this.entranceyear = source.readString();
        this.sdtno = source.readString();
        this.qq = source.readString();
        this.contact = source.readString();
        this.contactmobile = source.readString();
        this.iskitchener = source.readString();
        this.ordnum = source.readString();
        this.dishes = source.readString();
        this.videourl = source.readString();
        this.identitycard = source.readString();
        this.kitchencard = source.readString();
        this.healthcard = source.readString();
        this.token = source.readString();
        this.ucrtoken = source.readString();
        this.ucropenid = source.readString();
        this.msgsetting = source.readParcelable(MsgsettingBean.class.getClassLoader());
    }

    public static final Creator<ImUserData> CREATOR = new Creator<ImUserData>() {
        @Override
        public ImUserData createFromParcel(Parcel source) {
            return new ImUserData(source);
        }

        @Override
        public ImUserData[] newArray(int size) {
            return new ImUserData[size];
        }
    };

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPinyinname() {
        return pinyinname;
    }

    public void setPinyinname(String pinyinname) {
        this.pinyinname = pinyinname;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getIdenum() {
        return idenum;
    }

    public void setIdenum(String idenum) {
        this.idenum = idenum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getWebauth() {
        return webauth;
    }

    public void setWebauth(String webauth) {
        this.webauth = webauth;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public String getLogintimes() {
        return logintimes;
    }

    public void setLogintimes(String logintimes) {
        this.logintimes = logintimes;
    }

    public String getIsonline() {
        return isonline;
    }

    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    public String getIssuper() {
        return issuper;
    }

    public void setIssuper(String issuper) {
        this.issuper = issuper;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public MsgsettingBean getMsgsetting() {
        return msgsetting;
    }

    public void setMsgsetting(MsgsettingBean msgsetting) {
        this.msgsetting = msgsetting;
    }

    public String getDynamicsetting() {
        return dynamicsetting;
    }

    public void setDynamicsetting(String dynamicsetting) {
        this.dynamicsetting = dynamicsetting;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getCustsource() {
        return custsource;
    }

    public void setCustsource(String custsource) {
        this.custsource = custsource;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajorclass() {
        return majorclass;
    }

    public void setMajorclass(String majorclass) {
        this.majorclass = majorclass;
    }

    public String getEntranceyear() {
        return entranceyear;
    }

    public void setEntranceyear(String entranceyear) {
        this.entranceyear = entranceyear;
    }

    public String getSdtno() {
        return sdtno;
    }

    public void setSdtno(String sdtno) {
        this.sdtno = sdtno;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactmobile() {
        return contactmobile;
    }

    public void setContactmobile(String contactmobile) {
        this.contactmobile = contactmobile;
    }

    public String getIskitchener() {
        return iskitchener;
    }

    public void setIskitchener(String iskitchener) {
        this.iskitchener = iskitchener;
    }

    public String getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(String ordnum) {
        this.ordnum = ordnum;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public String getKitchencard() {
        return kitchencard;
    }

    public void setKitchencard(String kitchencard) {
        this.kitchencard = kitchencard;
    }

    public String getHealthcard() {
        return healthcard;
    }

    public void setHealthcard(String healthcard) {
        this.healthcard = healthcard;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUcrtoken() {
        return ucrtoken;
    }

    public void setUcrtoken(String ucrtoken) {
        this.ucrtoken = ucrtoken;
    }

    public String getUcropenid() {
        return ucropenid;
    }

    public void setUcropenid(String ucropenid) {
        this.ucropenid = ucropenid;
    }


    public static class MsgsettingBean extends Data {
        /**
         * shock : 0
         * beep : 0
         * push : 0
         */

        private String shock;
        private String beep;
        private String push;

        public String getShock() {
            return shock;
        }

        public void setShock(String shock) {
            this.shock = shock;
        }

        public String getBeep() {
            return beep;
        }

        public void setBeep(String beep) {
            this.beep = beep;
        }

        public String getPush() {
            return push;
        }

        public void setPush(String push) {
            this.push = push;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.shock);
            dest.writeString(this.beep);
            dest.writeString(this.push);
        }

        public MsgsettingBean() {

        }

        public MsgsettingBean(Parcel source) {
            source.writeString(this.getShock());
            source.writeString(this.getBeep());
            source.writeString(this.getPush());
        }

        public static final Creator<MsgsettingBean> CREATOR = new Creator<MsgsettingBean>() {
            @Override
            public MsgsettingBean createFromParcel(Parcel source) {
                return new MsgsettingBean(source);
            }

            @Override
            public MsgsettingBean[] newArray(int size) {
                return new MsgsettingBean[size];
            }
        };
    }

    private class BackgroundImg {
    }
}
