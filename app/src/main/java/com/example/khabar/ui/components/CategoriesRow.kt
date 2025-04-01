package com.example.khabar.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.khabar.data.model.Category

private val category = listOf("general", "business", "entertainment", "health", "science", "sports", "technology")

@Composable
fun CategoriesRow(
    onCategorySelected: (Category) -> Unit
){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        userScrollEnabled = true
    ) {
        items(category.size) { index ->
            CategoryItem(
                category = category[index],
                isSelected = category[0] == "general",
                onCategorySelected = onCategorySelected
            )
            }
    }
}

@Composable
fun CategoryItem(
    category: String,
    isSelected: Boolean,
    onCategorySelected: (Category) -> Unit
){
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = { onCategorySelected(Category(category, category)) }
    ) {
        Text(text = category)
    }
}

@Preview
@Composable
fun CategoryItemPreview(){
    CategoriesRow(
        onCategorySelected = {}
    )
}