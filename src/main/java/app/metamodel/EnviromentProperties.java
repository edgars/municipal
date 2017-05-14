/* 
*  GreenBox is free software; you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation version 2.
*
*   GreenBox is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with GreenBox; if not, write to the Free Software
*   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package app.metamodel;

/**
 * <p>Project : GreenBox </p>
 *
 * @author Edgar Silva
 * @version 1.0
 * @since 2002
 * <p>
 * The objective of this class is to read properties located in files,
 * to get some important informations to project.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class EnviromentProperties {
    private static EnviromentProperties instance;
    protected Properties properties = new Properties();


    private EnviromentProperties() {
        try {
            //intializing propeties
            properties.load(new FileInputStream("mapping.properties"));

        } catch (IOException e) {
            try {
                properties.load(EnviromentProperties.class.getResourceAsStream("/mapping/mapping.properties"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Pure Singleton
    public static EnviromentProperties getInstance() {
        return (instance == null) ? instance = new EnviromentProperties() : instance;
    }

    public String replaceDataType(String jdbcType) {
        return properties.getProperty(jdbcType);
    }

}
