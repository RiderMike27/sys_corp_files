<?php
include('core/connection.php');
$dbconn = getConnection();

/*
* LOGIN
*/

// Check if the form was sent and the action is LOGIN
if(isset($_GET['accion']) AND $_GET['accion'] == "ingresar")
{
	$nick = mb_strtoupper(trim($_GET['nick']), 'UTF-8');
	$pass = sha1($_GET['pass']);  

	// prepare statement for search user
	$stmt = $dbconn->prepare("SELECT * FROM usuario WHERE nick = :nick AND pass = :pass");
	// bind value to the :id parameter
	$stmt->bindValue(':nick', $nick);
	$stmt->bindValue(':pass', $pass);
	// execute the statement
	$stmt->execute();
	// return the result set as an object
	$user = $stmt->fetchObject();

	if($user != FALSE){	// Authorized access
        $response = array("status" => "success",  "message" => "inicio correcto", "nombre"=>$user->nombre,"id_usuario"=>$user->id_usuario);
	}
	else // User not authorized
	{
		$response = array("status" => "error", "message" => "error de inicio");
	}
	print json_encode($response);
}
else // FORM NOT SENT
{
	print json_encode(array("status" => "error", "message" => "Formulario no enviado"));
}

?>