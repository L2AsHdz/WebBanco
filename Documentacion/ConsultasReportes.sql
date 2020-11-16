--Reportes gerente
SELECT c.*, u.`tipoUsuario` FROM cambioRealizado c INNER JOIN usuario u ON c.codUsuarioModificado=u.codigo WHERE u.tipoUsuario = 2;

SELECT u.*, c.birth from usuario u inner join cliente c on u.codigo=c.codigoUsuario inner join cuenta ct on c.codigoUsuario=ct.codigoCliente inner join transaccion t on ct.codigo=t.codCuenta where t.monto>150 group by c.codigoUsuario;

SELECT u.*, c.birth, SUM(t.monto) from usuario u inner join cliente c on u.codigo=c.codigoUsuario inner join cuenta ct on c.codigoUsuario=ct.codigoCliente inner join transaccion t on ct.codigo=t.codCuenta group by c.codigoUsuario HAVING SUM(t.monto) > 450;

SELECT u.*, c.birth, SUM(ct.saldo) saldoT FROM usuario u INNER JOIN cliente c ON u.codigo=c.codigoUsuario INNER JOIN cuenta ct ON c.codigoUsuario=ct.codigoCliente GROUP BY ct.codigoCliente ORDER BY saldoT DESC LIMIT 10;

SELECT t.monto, u.*, c.birth from usuario u inner join cliente c on u.codigo=c.codigoUsuario inner join cuenta ct on c.codigoUsuario=ct.codigoCliente left join transaccion t on ct.codigo=t.codCuenta where t.codigo IS NULL AND (t.fecha BETWEEN '2020-01-01' AND '2020-12-05');

select t.* from transaccion t inner join cuenta c on t.codCuenta=c.codigo where c.codigoCliente=332;
select u.nombre, t.* from transaccion t inner join cuenta c on t.codCuenta=c.codigo inner join usuario u on c.codigoCliente=u.codigo where u.nombre like '%maria%'

select COUNT(tr.codigo) transacciones, u.*, t.nombre from usuario u inner join empleado e on u.codigo=e.codigoUsuario inner join turno t on e.idTurno=t.id inner join transaccion tr on u.codigo=tr.codCajero where tr.fecha between '2020-01-01' and '2020-12-05' group by u.codigo order by transacciones desc limit 1;

--Reportes cajero
select * from transaccion where codCajero = 101 and fecha = '2020-11-15';
select tipo, SUM(monto) from transaccion where codCajero = 101 and fecha = '2020-11-15' group by tipo;

--Reportes cliente
select * from transaccion t inner join cuenta c on t.codCuenta=c.codigo where c.codigoCliente=331 and (t.fecha between DATE_SUB(NOW(), interval 1 YEAR) AND NOW()) order by t.monto desc limit 15;

select * from transaccion t inner join cuenta c on t.codCuenta=c.codigo where c.codigoCliente=331 and (t.fecha between '2020-01-01' and '2020-05-05');

select * from cuenta where codigoCliente=331 order by saldo desc limit 1;
select * from transaccion where codCuenta=1234 and (fecha between '2020-05-05' and now());

select s.* from solicitudAsociacion s inner join cuenta c on s.codCuenta=c.codigo where c.codigoCliente=332;

select * from solicitudAsociacion where codCliente = 331;

