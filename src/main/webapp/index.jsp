<html>
 <body>

<% if (request.getAttribute("error")!=null) { %>
 <h1><%=request.getAttribute("error")%><h1/>
     <img src="https://www.irishtimes.com/polopoly_fs/1.2943353.1484846599!/image/image.jpg_gen/derivatives/box_620_330/image.jpg">
     <%}%>
 <form action="<%=request.getContextPath() + "/selectstorage"%>" method="post">
    <table width="100%" border="1" style="width: 400px; padding-top: 300px">
     <tr>
      <td colspan="2">Select storage type</td>
     </tr>

     <tr>
      <td>storage type</td>
      <td>
        <select name="storageType">
            <option value="BULLSHIT"></option>
         <option value="ARRAY">ARRAY</option>
         <option value="COLLECTION">COLLECTION</option>
         <option value="DB">DB</option>
        </select>
      </td>
     </tr>
    </table>

     <input type="submit" value="SELECT"/>
 </form>

 </body>
</html>