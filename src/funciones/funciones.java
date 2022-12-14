package funciones;

import datos.SMTPenviar;
import negocio.Gestionar_Usuario;
import negocio.Gestionar_Direccion;
import negocio.Gestionar_Adquisicion;
import negocio.Gestionar_Revaluo;
import negocio.Gestionar_Fotografia;
import negocio.Gestionar_Grupo;
import negocio.Gestionar_Estado;
import negocio.Gestionar_Responsable;
import negocio.Gestionar_Inmueble;
import negocio.Gestionar_Reporte;
import negocio.Gestionar_Informe;

public class funciones {

    Gestionar_Usuario gu = new Gestionar_Usuario();
    Gestionar_Grupo gs = new Gestionar_Grupo();
    Gestionar_Direccion ga = new Gestionar_Direccion();
    Gestionar_Estado ge = new Gestionar_Estado();
    Gestionar_Adquisicion gcom = new Gestionar_Adquisicion();
    Gestionar_Revaluo gm = new Gestionar_Revaluo();
    Gestionar_Responsable gr = new Gestionar_Responsable();
    Gestionar_Fotografia gp = new Gestionar_Fotografia();
    Gestionar_Inmueble gi = new Gestionar_Inmueble();
    Gestionar_Reporte grp = new Gestionar_Reporte();
    Gestionar_Informe inf = new Gestionar_Informe();

    cadenas cad = new cadenas();
                                  // LISTUSU["*"]     todo el correo           
    public void EjecOperacion(String sub, String msj) {
        String respuesta = "";
        String pat = "";
        String s = cad.identOper(sub);//LISTUSU obtiene el comando sin parametros
        System.out.println("operador capturado " + s);
        s = s.toUpperCase(); //LISTUSU
        if (-1 == sub.indexOf("[")) {
            respuesta = "error falta corchetes []";
        } else {
            pat = cad.ObtPatron(sub);///["....."] => ["*"]
            System.out.println("patron capturado  " + pat);
            int h = 0;

        }
        switch (s) {
///GESTIONAR USUARIOS
            case "LISTUSU"://procedimiento LISTUSU["*"]                         //res tabla con sus estilos
                System.out.println("LISTANDO USUARIOS REGISTRADOS: \n" + gu.ListarUsuarios());
                respuesta = gu.ListarUsuarios();
                                //res tabla con sus estilos
                break;
            case "INSUSU":
                System.out.println("INSERTANDO USUARIO");
                gu.AsigParametros(pat);
                if (-1 != gu.InsertarUsuario()) {
                    respuesta = "<b>USUARIO NUEVO INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR USUARIO</b>";
                }
                break;
            case "ELIUSU":
                gu.setIdUsuario(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gu.EliminarUsuario()) {
                    System.out.println("USUARIO ELIMINADO");
                    respuesta = "<b>USUARIO ELIMINADO</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR USUARIO ");
                    respuesta = "<b>ERROR AL ELIMINAR USUARIO</b>";
                }
                break;
            case "MODUSU":
                gu.AsigParametrosMod(pat);
                if (-1 != gu.ModifUsuario()) {
                    System.out.println("USUARIO MODIFICADO ");
                    respuesta = "<b>USUARIO MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR USUARIO");
                    respuesta = "<b>ERROR AL MODIFICAR USUARIO</b>";
                }
                break;

/// GESTIONAR GRUPOS
            case "INSGRUP":
                System.out.println("INSERTANDO GRUPO");
                gs.AsigParametros(pat);
                if (-1 != gs.InsertarGrupo()) {
                    respuesta = "<b>GRUPO NUEVO INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR GRUPO</b>";
                }
                break;
            case "LISTGRUP":
                System.out.println("LISTANDO GRUPOS REGISTRADOS: \n" + gs.ListarGrupo());
                respuesta = gs.ListarGrupo();
                break;
            case "ELIGRUP":
                gs.setIdGrupo(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gs.EliminarGrupo()) {
                    System.out.println("GRUPO ELIMINADO ");
                    respuesta = "<b>GRUPO ELIMINADO</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR GRUPO");
                    respuesta = "<b>GRUPO NO ELIMINADO</b>";
                }
                break;
            case "MODGRUP":
                gs.AsigParametrosMod(pat);
                if (-1 != gs.ModifGrupo()) {
                    System.out.println("GRUPO MODIFICADO ");
                    respuesta = "<b>GRUPO MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR GRUPO");
                    respuesta = "<b>GRUPO NO MODIFICADO</b>";
                }
                break;
//GESTIONAR DIRECCION
            case "INSDIR":
                System.out.println("INSERTANDO DIRECCION");
                ga.AsigParametros(pat);
                if (-1 != ga.InsertarDireccion()) {
                    respuesta = "<b>DIRECCION NUEVA INSERTADA</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR DIRECCION</b>";
                }
                break;
            case "LISTDIR"://procedimiento LISTUSU["*"]
                System.out.println("LISTANDO DIRECCIONES REGISTRADAS: \n" + ga.ListarDireccion());
                respuesta = ga.ListarDireccion();
                break;
            case "MODDIR":
                ga.AsigParametrosMod(pat);
                if (-1 != ga.ModifDireccion()) {
                    System.out.println("DIRECCION MODIFICADA ");
                    respuesta = "<b>DIRECCION MODIFICADA</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR DIRECCION");
                    respuesta = "<b>ERROR AL MODIFICAR DIRECCION</b>";
                }
                break;
            case "ELIDIR":
                ga.setIdDireccion(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != ga.EliminarDireccion()) {
                    System.out.println("ELIMINANDO DIRECCION ");
                    respuesta = "<b>DIRECCION ELIMINADA</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR DIRECCION ");
                    respuesta = "<b>ERROR DIRECCION NO ELIMINADA</b>";
                }
                break;

/// GESTIONAR ESTADO
            case "INSEST":
                System.out.println("INSERTANDO ESTADO");
                ge.AsigParametros(pat);
                if (-1 != ge.InsertarEstado()) {
                    respuesta = "<b>ESTADO NUEVO INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR ESTADO</b>";
                }
                break;
            case "LISTEST":
                System.out.println("LISTANDO ESTADO REGISTRADOS: \n" + ge.ListarEstado());
                respuesta = ge.ListarEstado();
                break;
            case "ELIEST":
                ge.setIdEstado(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != ge.EliminarEstado()) {
                    System.out.println("ESTADO ELIMINADO ");
                    respuesta = "<b>ESTADO ELIMINADO</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR ESTADO");
                    respuesta = "<b>ESTADO NO ELIMINADO</b>";
                }
                break;
            case "MODEST":
                ge.AsigParametrosMod(pat);
                if (-1 != ge.ModifEstado()) {
                    System.out.println("ESTADO MODIFICADO ");
                    respuesta = "<b>ESTADO MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR ESTADO");
                    respuesta = "<b>ESTADO NO MODIFICADO</b>";
                }
                break;
//GESTIONAR ADQUISICION
            case "INSADQ":
                System.out.println("INSERTANDO ADQUISICION");
                gcom.AsigParametros(pat);
                if (-1 != gcom.InsertarAdquisicion()) {
                    respuesta = "<b>ADQUISICION NUEVA INSERTADA</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR ADQUISICION</b>";
                }
                break;
            case "LISTADQ":
                System.out.println("LISTANDO ADQUISICIONES REGISTRADAS: \n" + gcom.ListarAdquisicion());
                respuesta = gcom.ListarAdquisicion();
                break;
            case "ELIADQ":
                gcom.setIdAdquisicion(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gcom.EliminarAdquisicion()) {
                    System.out.println("ADQUISICION ELIMINADA ");
                    respuesta = "<b>ADQUISICION ELIMINADA</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR ADQUISICION");
                    respuesta = "<b>ADQUISICION NO ELIMINADA</b>";
                }
                break;
            case "MODADQ":
                gcom.AsigParametrosMod(pat);
                if (-1 != gcom.ModifAquisicion()) {
                    System.out.println("ADQUISICION MODIFICADA ");
                    respuesta = "<b>ADQUISICION MODIFICADA</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR ADQUISICION");
                    respuesta = "<b>ADQUISICION NO MODIFICADA</b>";
                }
                break;
///GESTIONAR REVALUO
            case "INSREV":
                System.out.println("INSERTANDO REVALUO");
                gm.AsigParametros(pat);
                if (-1 != gm.InsertarRevaluo()) {
                    respuesta = "<b>REVALUO NUEVO INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR REVALUO</b>";
                }
                break;
            case "LISTREV"://procedimiento LISTMEN["*"]
                System.out.println("LISTANDO REVALUOS: \n" + gm.ListarRevaluo());
                respuesta = gm.ListarRevaluo();

                break;
            case "ELIREV":
                gm.setIdRevaluo(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gm.EliminarRevaluo()) {
                    System.out.println("REVALUO ELIMINADO ");
                    respuesta = "<b>REVALUO ELIMINADO<b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR REVALUO ");
                    respuesta = "<b>REVALUO NO ELIMINADO</b>";
                }
                break;
            case "MODREV":
                gm.AsigParametrosMod(pat);
                if (-1 != gm.ModifRevaluo()) {
                    System.out.println("REVALUO MODIFICADO ");
                    respuesta = "<b>REVALUO MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR REVALUO");
                    respuesta = "<b>REVALUO NO MODIFICADO</b>";
                }
                break;

/// GESTIONAR RESPONSABLE
            case "INSRESP":
                System.out.println("INSERTANDO RESPONSABLE");
                gr.AsigParametros(pat);
                if (-1 != gr.InsertarResponsable()) {
                    respuesta = "<b>REPONSABLE NUEVO INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR RESPONSABLE</b>";
                }
                break;
            case "LISTRESP":
                System.out.println("LISTANDO RESPONSABLE REGISTRADOS: \n" + gr.ListarResponsable());
                respuesta = gr.ListarResponsable();
                break;
            case "ELIRESP":
                gr.setIdResposable(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gr.EliminarResponsable()) {
                    System.out.println("RESPONSABLE ELIMINADO ");
                    respuesta = "<b>RESPONSABLE ELIMINADO</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR RESPONSABLE");
                    respuesta = "<b>RESPONSABLE NO ELIMINADO</b>";
                }
                break;
            case "MODRESP":
                gr.AsigParametrosMod(pat);
                if (-1 != gr.ModifResponsable()) {
                    System.out.println("RESPONSABLE MODIFICADO ");
                    respuesta = "<b>RESPONSABLE MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR RESPONSABLE");
                    respuesta = "<b>RESPONSABLE NO MODIFICADO</b>";
                }
                break;
///GESTIONAR FOTOGRAFIA
            case "INSFOTO":
                System.out.println("INSERTANDO FOTOGRAFIA");
                gp.AsigParametros(pat);
                if (-1 != gp.InsertarFotografia()) {
                    respuesta = "<b>FOTOGRAFIA NUEVA INSERTADA</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR FOTOGRAFIA</b>";
                }
                break;
            case "LISTFOTO":
                System.out.println("LISTANDO FOTOGRAFIAS: \n" + gp.ListarFotografia());
                respuesta = gp.ListarFotografia();
                break;
            case "ELIFOTO":
                gp.setIdFotografia(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gp.EliminarFotografia()) {
                    System.out.println("FOTOGRAFIA ELIMINADA ");
                    respuesta = "<b>FOTOGRAFIA ELIMINADA</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR FOTOGRAFIA ");
                    respuesta = "<b>FOTOGRAFIA NO ELIMINADA</b>";
                }
                break;
            case "MODFOTO":
                gp.AsigParametrosMod(pat);
                if (-1 != gp.ModifFotografia()) {
                    System.out.println("FOTOGRAFIA MODIFICADA ");
                    respuesta = "<b>FOTOGRAFIA MODIFICADA</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR FOTOGRAFIA");
                    respuesta = "<b>FOTOGRAFIA NO MODIFICADA</b>";
                }
                break;
///GESTIONAR INMUEBLE
            case "INSINMU":
                System.out.println("INSERTANDO INMUEBLE");
                gi.AsigParametros(pat);
                if (-1 != gi.InsertarInmueble()) {
                    respuesta = "<b>INMUEBLE NUEVO INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR INMUEBLE</b>";
                }
                break;
            case "LISTINMU":
                System.out.println("LISTANDO INMUEBLES: \n" + gi.ListarInmueble());
                respuesta = gi.ListarInmueble();
                break;
            case "ELIINMU":
                gi.setIdInmueble(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != gi.EliminarInmueble()) {
                    System.out.println("INMUEBLE ELIMINADO ");
                    respuesta = "<b>INMUEBLE ELIMINADO</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR INMUEBLE ");
                    respuesta = "<b>INMUEBLE NO ELIMINADO</b>";
                }
                break;
            case "MODINMU":
                gi.AsigParametrosMod(pat);
                if (-1 != gi.ModifInmueble()) {
                    System.out.println("INMUEBLE MODIFICADO");
                    respuesta = "<b>INMUEBLE MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR INMUEBLE");
                    respuesta = "<b>ERROR AL MODIFICAR INMUEBLE</b>";
                }
                break;
/// GESTIONAR INFORME
            case "INSINFOR":
                System.out.println("INSERTANDO INFORME");
                inf.AsigParametros(pat);
                if (-1 != inf.InsertarInforme()) {
                    respuesta = "<b>NUEVO INFORME INSERTADO</b>";
                } else {
                    respuesta = "<b>ERROR AL INSERTAR EL INFORME</b>";
                }
                break;
            case "LISTINFOR":
                System.out.println("LISTANDO INFORMES REGISTRADOS: \n" + inf.ListarInforme());
                respuesta = inf.ListarInforme();
                break;
            case "ELIINFOR":
                inf.setIdInforme(Integer.parseInt(cad.ObtenerID(pat)));
                if (-1 != inf.EliminarInforme()) {
                    System.out.println("INFORME ELIMINADO ");
                    respuesta = "<b>INFORME ELIMINADO</b>";
                } else {
                    System.out.println("ERROR AL ELIMINAR INFORME");
                    respuesta = "<b>INFORME NO ELIMINADO</b>";
                }
                break;
            case "MODINFOR":
                inf.AsigParametrosMod(pat);
                if (-1 != inf.ModifInforme()) {
                    System.out.println("INFORME MODIFICADO ");
                    respuesta = "<b>INFORME MODIFICADO</b>";
                } else {
                    System.out.println("ERROR AL MODIFICAR INFORME");
                    respuesta = "<b>INFORME NO MODIFICADO</b>";
                }
                break;
///GESTIONAR REPORTES
            case "REPREV":
                System.out.println("REPORTE REVALUO: \n");
                grp.setAnioRevaluo(cad.ObtenerID(pat));
                respuesta = grp.listRepRevaluo();
                break;
            case "REPGRUP":
                System.out.println("REPORTE GRUPO: \n" + gi.ListarInmueble());
                grp.setGrupInmueble(cad.ObtenerID(pat));
                respuesta = grp.listRepGrupo();
                break;
///AYUDA
            case "HELP":
                respuesta
                        = "<h1 style=\"color:#8fe5f6; text-align:center;\">Sistema Mail de Gesti?n - Bienes Inmuebles</h1><br/>"
                        + "<h3 style=\"color:black;\">Comandos:</h3><br/>"
                        + "<h3>1.- USUARIOS</h3><ol>"
                        + " <li>Listar:    LISTUSU[\"*\"] </li>"
                        + " <li>Insertar:  INSUSU[\"ID\",\"Nombre\",\"CI\",\"Email\",\"Password\",\"TipoUsuario\"] </li>"
                        + " <li>Modificar: MODUSU[\"CI\",\"Nombre\",\"Email\",\"Password\",\"TipoUsuario\"] </li>"
                        + " <li>Eliminar:  ELIUSU[\"ID\"]  </li>"
                        + "</ol>"
                        + "<h3>2.- GRUPO</h3><ol>"
                        + "<li>Listar:    LISTGRUP[\"*\"] </li>"
                        + "<li>Insertar:  INSGRUP[\"ID\",\"Grupo\"] </li>"
                        + "<li>Modificar: MODGRUP[\"ID\",\"Grupo\"] </li>"
                        + "<li>Eliminar:  ELIGRUP[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>3.- DIRECCION</h3><ol>"
                        + "<li>Listar:    LISTDIR[\"*\"] </li>"
                        + "<li>Insertar:  INSDIR[\"ID\",\"Ubicacion\",\"Lugar\",\"Edificio\",\"Latitud\",\"Longitud\"]</li>"
                        + "<li>Modificar: MODDIR[\"ID\",\"Ubicacion\",\"Lugar\",\"Edificio\",\"Latitud\",\"Longitud\"]</li>"
                        + "<li>Eliminar:  ELIDIR[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>4.- ESTADO</h3><ol>"
                        + "<li>Listar:    LISTEST[\"*\"] </li>"
                        + "<li>Insertar:  INSEST[\"ID\",\"Nombre\"] </li>"
                        + "<li>Modificar: MODEST[\"ID\",\"Nombre\"] </li>"
                        + "<li>Eliminar:  ELIEST[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>5.- ADQUISICION</h3><ol>"
                        + "<li>Listar:    LISTADQ[\"*\"]</li>"
                        + "<li>Insertar:  INSADQ[\"ID\",\"TipoIngreso\",\"ModoIngreso\",\"Recurso\",\"Fecha\",\"Monto\"] </li>"
                        + "<li>Modificar: MODADQ[\"ID\",\"TipoIngreso\",\"ModoIngreso\",\"Recurso\",\"Fecha\",\"Monto\"] </li>"
                        + "<li>Eliminar:  ELIADQ[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>6.- RESPONSABLE</h3><ol>"
                        + "<li>Listar:    LISTRESP[\"*\"] </li>"
                        + "<li>Insertar:  INSRESP[\"ID\",\"CodAdministrativo\",\"Nombre\"] </li>"
                        + "<li>Modificar: MODRESP[\"ID\",\"CodAdministrativo\",\"Nombre\"] </li>"
                        + "<li>Eliminar:  ELIRESP[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>7.- INMUEBLE</h3><ol>"
                        + "<li>Listar:      LISTINMU[\"*\"] </li>"
                        + "<li>Insertar:    INSINMU[\"ID\",\"Codigo\",\"Detalle\",\"Fecha\",\"IdResponsable\",\"IdEstado\",\"IdGrupo\",\"IdDireccion\",\"IdAdquisicion\"] </li>"
                        + "<li>Modificar:   MODINMU[\"ID\",\"Codigo\",\"Detalle\",\"Fecha\",\"IdResponsable\",\"IdEstado\",\"IdGrupo\",\"IdDireccion\",\"IdAdquisicion\"] </li>"
                        + "<li>Eliminar:    ELIINMU[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>8.- FOTOGRAFIA</h3><ol>"
                        + "<li>Listar:      LISTFOTO[\"*\"] </li>"
                        + "<li>Insertar:    INSFOTO[\"ID\",\"Direccion\",\"Nombre\",\"Fecha\",\"IdInmuebleAsociado\"] </li>"
                        + "<li>Modificar:   MODFOTO[\"ID\",\"Direccion\",\"Nombre\",\"Fecha\",\"IdInmuebleAsociado\"] </li>"
                        + "<li>Eliminar:    ELIFOTO[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>9.- REVALUO</h3><ol>"
                        + "<li>Listar:    LISTREV[\"*\"] </li>"
                        + "<li>Insertar:  INSREV[\"ID\",\"FechaRevaluo\",\"Costo\",\"CostoActualizado\",\"DepreciacionAcu\",\"ValorNeto\",\"IdInmueble\"] </li>"
                        + "<li>Modificar: MODREV[\"ID\",\"FechaRevaluo\",\"Costo\",\"CostoActualizado\",\"DepreciacionAcu\",\"ValorNeto\",\"IdInmueble\"] </li>"
                        + "<li>Eliminar:  ELIREV[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>10.- INFORME</h3><ol>"
                        + "<li>Listar:      LISTINFOR[\"*\"] </li>"
                        + "<li>Insertar:    INSINFOR[\"ID\",\"Descripcion\",\"Url\",\"IdRevaluo\"] </li>"
                        + "<li>Modificar:   MODINFOR[\"ID\",\"Descripcion\",\"Url\",\"IdRevaluo\"] </li>"
                        + "<li>Eliminar:    ELIINFOR[\"ID\"] </li>"
                        + "</ol>"
                        + "<h3>11.- REPORTES</h3><ol>"
                        + "<li>Reporte Revaluo:      REPREV[\"Gestion Revaluo\"] </li>"
                        + "<li>Reporte Grupos:    REPGRUP[\"Grupo\"] </li>"
                        + "</ol>"
                        + "<br>";
                break;
            default:
                respuesta = "<b>ERROR DE COMANDO: NO EXISTE EL COMANDO SOLICITADO</b>";
                break;
        }
        SMTPenviar smtp = new SMTPenviar();
        //smtp.enviarGmail(cad.ObtEmailDestino(msj), respuesta);
        smtp.sendMail(cad.ObtEmailDestino(msj), respuesta);

    }

}
