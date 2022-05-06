using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;

namespace Hello_Rider
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            //setarea titlului consolei
            Console.Title = "Hello ADO.NET";
            //setarea culorii de fundal a consolei
            //Console.BackgroundColor = ConsoleColor.White;
            //setarea culorii caracterelor afisate in consola
            //Console.ForegroundColor = ConsoleColor.Blue;
            Console.Clear();
            Console.WriteLine("Hello ADO.NET");
            Console.WriteLine("Hello Ioana");
            //pentru ca aplicatia sa functioneze corect, va trebui sa specificati numele server-ului de baze de date personal
            string connectionString =
                @"Server=DESKTOP-DA6UVCS;Initial Catalog=Seminar1SGBD;Integrated Security=true;";
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    Console.WriteLine("Starea conexiunii: {0}", connection.State);
                    connection.Open();
                    Console.WriteLine("Starea conexiunii dupa apelul metodei Open(): {0}", connection.State);
                    //inserarea unei noi inregistrari in tabel
                    SqlCommand insertCommand = new SqlCommand("INSERT INTO Produse (nume, pret, marca) VALUES " +
                                                              "(@nume, @pret, @marca);", connection);
                    insertCommand.Parameters.AddWithValue("@nume", "iphone");
                    insertCommand.Parameters.AddWithValue("@pret", 4000.0F);
                    insertCommand.Parameters.AddWithValue("@marca", "Apple");
                    insertCommand.ExecuteNonQuery();
                    //citirea datelor din tabel si afisarea acestora in consola
                    SqlCommand selectCommand = new SqlCommand("SELECT nume, pret, marca FROM Produse;", connection);
                    SqlDataReader reader = selectCommand.ExecuteReader();
                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            Console.WriteLine("{0}\t{1}\t{2}", reader.GetString(0), reader.GetFloat(1),
                                reader.GetString(2));
                        }
                    }

                    reader.Close();
                    //actualizarea datelor din tabel
                    SqlCommand updateCommand =
                        new SqlCommand("UPDATE Produse SET pret=@pretnou WHERE nume=@nume;", connection);
                    updateCommand.Parameters.AddWithValue("@nume", "iphone");
                    updateCommand.Parameters.AddWithValue("@pretnou", 5000F);
                    updateCommand.ExecuteNonQuery();
                    //citirea datelor din tabel si afisarea acestora in consola
                    reader = selectCommand.ExecuteReader();
                    if (reader.HasRows)
                    {
                        while (reader.Read())
                        {
                            Console.WriteLine("{0}\t{1}\t{2}", reader.GetString(0), reader.GetFloat(1),
                                reader.GetString(2));
                        }
                    }

                    reader.Close();
                    //stergerea datelor din tabel
                    SqlCommand deleteCommand = new SqlCommand("DELETE FROM Produse WHERE nume=@nume;", connection);
                    deleteCommand.Parameters.AddWithValue("@nume", "iphone");
                    int numarRanduriSterse = deleteCommand.ExecuteNonQuery();
                    Console.WriteLine("Numarul de inregistrari sterse: {0}", numarRanduriSterse);
                }
            }
            catch (Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("Mesajul erorii care ne-a adus in catch este: {0}", ex.Message);
            }
        }
    }
}    
       
