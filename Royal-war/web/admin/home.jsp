<%-- 
    Document   : home
    Created on : Nov 16, 2011, 12:32:48 AM
    Author     : MesutOezil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib prefix="p" uri="http://primefaces.prime.com.tr/ui" %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<p:resources/>
<div class="left" id="main_panel_container">
    <div id="dashboard">
        <h2 class="ico_mug">Dashboard</h2>
        <div class="clearfix">
            <div class="left quickview">
                <h3>Overview</h3>
                <ul>
                    <li>Total Posts: <span class="number">15</span></li>
                    <li>Total Comments: <span class="number">340</span></li>
                    <li>Drafts: <span class="number">3</span></li>
                    <li>Things to do: <span class="number">3</span></li>
                    <li>Comments waiting for aproval: <span class="number">20</span></li>
                    <li>Visits Today: <span class="number">230</span></li>
                </ul>
            </div>
            <div class="quickview left">
                <h3>Some data</h3>
                <ul>
                    <li>Users online: <span class="number">15</span></li>
                    <li>Trafic increase: <span class="number">34%</span></li>
                    <li>Photos: <span class="number">3</span></li>
                    <li>Things to do: <span class="number">3</span></li>
                    <li>Photos waiting for aproval: <span class="number">31</span></li>
                    <li>Visits Today: <span class="number">230</span></li>
                </ul>
            </div>
            <div class="left" id="chart">
                <h3>Visits today</h3>
                <div id="placeholder" style="position: relative;"><canvas height="95" width="180"></canvas><canvas height="95" width="180" style="position:absolute;left:0px;top:0px;"></canvas><div style="font-size:smaller;color:#000" class="tickLabels"><div class="tickLabel" style="position:absolute;top:79px;left:2px;width:30px;text-align:center">0.0</div><div class="tickLabel" style="position:absolute;top:79px;left:79.5px;width:30px;text-align:center">0.5</div><div class="tickLabel" style="position:absolute;top:79px;left:157px;width:30px;text-align:center">1.0</div><div class="tickLabel" style="position:absolute;top:66px;right:168px;width:12px;text-align:right">0</div><div class="tickLabel" style="position:absolute;top:33px;right:168px;width:12px;text-align:right">5</div><div class="tickLabel" style="position:absolute;top:0px;right:168px;width:12px;text-align:right">10</div></div></div><!-- CHART -->
                <a class="ico_chart more" href="#">Click to see more</a>
            </div>	
        </div>
    </div><!-- end #dashboard -->



    <div class="clearfix" id="shortcuts">
        <h2 class="ico_mug">Panel shortcuts</h2>
        <ul>
            <li class="first_li"><a href=""><img alt="themes" src="img/theme.jpg"><span>Themes</span></a></li>
            <li><a href=""><img alt="statistics" src="img/statistic.jpg"><span>Statistics</span></a></li>
            <li><a href=""><img alt="FTP" src="img/ftp.jpg"><span>FTP</span></a></li>
            <li><a href=""><img alt="Users" src="img/users.jpg"><span>Users</span></a></li>
            <li><a href=""><img alt="Comments" src="img/comments.jpg"><span>Comments</span></a></li>
            <li><a href=""><img alt="Gallery" src="img/gallery.jpg"><span>Gallery</span></a></li>
            <li><a href=""><img alt="Security" src="img/security.jpg"><span>Security</span></a></li>

        </ul>
    </div><!-- end #shortcuts -->
</div>
<div class="right" id="sidebar">
    <h2 class="ico_mug">Sidebar</h2>
    <ul id="menu">
        <li>
            <a class="ico_posts" href="#">Posts</a>
            <ul style="display: block;">
                <li><a href="#">Edit posts</a></li>
                <li><a href="#">Add post</a></li>
                <li><a href="#">Manage posts</a></li>
            </ul>
            <a class="ico_page" href="#">Pages</a>
            <ul style="display: none;">
                <li><a href="#">Edit pages</a></li>
                <li><a href="#">Add page</a></li>
                <li><a href="#">Manage pages</a></li>
            </ul>
            <a class="ico_user" href="#">Users</a>
            <ul style="display: none;">
                <li><a href="#">Edit users</a></li>
                <li><a href="#">Add user</a></li>
                <li><a href="#">Manage users</a></li>
            </ul>
            <a class="ico_settings" href="#">Settings</a>
            <ul style="display: none;">
                <li><a href="#">Database</a></li>
                <li><a href="#">Themes</a></li>
                <li><a href="#">Options</a></li>
            </ul>
            <a class="ico_settings" href="#">Settings</a>
            <ul style="display: none;">
                <li><a href="#">Database</a></li>
                <li><a href="#">Themes</a></li>
                <li><a href="#">Options</a></li>
            </ul>
        </li>


    </ul>

</div>