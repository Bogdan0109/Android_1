using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SQLite;
using Newtonsoft.Json;
using System.IO;
using System.Windows.Forms;

namespace StoreApp
{
    public partial class MainForm : Form
    {
        private string connectionString = "Data Source=products.db;Version=3;";
        private List<Product> products;

        public MainForm()
        {
            InitializeComponent();
            LoadProducts();
        }

        private void LoadProducts()
        {
            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT * FROM Products";
                SQLiteDataAdapter adapter = new SQLiteDataAdapter(query, connection);
                DataTable dataTable = new DataTable();
                adapter.Fill(dataTable);

                products = new List<Product>();
                foreach (DataRow row in dataTable.Rows)
                {
                    products.Add(new Product
                    {
                        Id = Convert.ToInt32(row["Id"]),
                        Name = row["Name"].ToString(),
                        Price = Convert.ToDouble(row["Price"])
                    });
                }

                dataGridViewProducts.DataSource = products;
            }
        }

        private void btnSaveToJson_Click(object sender, EventArgs e)
        {
            SaveToJsonFile(products, "products.json");
            MessageBox.Show("Data saved to products.json.");
        }

        private void SaveToJsonFile<T>(T data, string fileName)
        {
            string json = JsonConvert.SerializeObject(data, Formatting.Indented);
            File.WriteAllText(fileName, json);
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            string name = txtName.Text;
            double price = Convert.ToDouble(txtPrice.Text);

            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                string query = "INSERT INTO Products (Name, Price) VALUES (@name, @price)";
                using (SQLiteCommand cmd = new SQLiteCommand(query, connection))
                {
                    cmd.Parameters.AddWithValue("@name", name);
                    cmd.Parameters.AddWithValue("@price", price);
                    cmd.ExecuteNonQuery();
                }
            }

            LoadProducts();
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            int id = Convert.ToInt32(txtId.Text);
            string name = txtName.Text;
            double price = Convert.ToDouble(txtPrice.Text);

            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                string query = "UPDATE Products SET Name=@name, Price=@price WHERE Id=@id";
                using (SQLiteCommand cmd = new SQLiteCommand(query, connection))
                {
                    cmd.Parameters.AddWithValue("@name", name);
                    cmd.Parameters.AddWithValue("@price", price);
                    cmd.Parameters.AddWithValue("@id", id);
                    cmd.ExecuteNonQuery();
                }
            }

            LoadProducts();
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            int id = Convert.ToInt32(txtId.Text);

            using (SQLiteConnection connection = new SQLiteConnection(connectionString))
            {
                connection.Open();
                string query = "DELETE FROM Products WHERE Id=@id";
                using (SQLiteCommand cmd = new SQLiteCommand(query, connection))
                {
                    cmd.Parameters.AddWithValue("@id", id);
                    cmd.ExecuteNonQuery();
                }
            }

            LoadProducts();
        }
    }

    public class Product
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public double Price { get; set; }
    }

    static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new MainForm());
        }
    }
}